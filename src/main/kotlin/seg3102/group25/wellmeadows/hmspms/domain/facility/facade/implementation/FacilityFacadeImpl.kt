package seg3102.group25.wellmeadows.hmspms.domain.facility.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.CreateDivisionDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.Shift
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.Division
import seg3102.group25.wellmeadows.hmspms.domain.facility.events.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.ShiftFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.ShiftRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import java.util.*

class FacilityFacadeImpl(
    private val facilityRepository: FacilityRepository,
    private val shiftRepository: ShiftRepository,
    private val facilityFactory: FacilityFactory,
    private val shiftFactory: ShiftFactory,
    private val eventEmitter: DomainEventEmitter
): FacilityFacade {
    override fun createWardDivision(wardInfo: CreateDivisionDTO): Boolean {
        val type: FacilityType = FacilityType.Ward
        type.setDivisionID(wardInfo.divisionId)
        type.setDivisionName(wardInfo.divisionName)
        val existAccount = facilityRepository.find(type)
        if (existAccount != null){
            return false
        }
        val facility = facilityFactory.createDivision(wardInfo)
        facilityRepository.save(facility)
        eventEmitter.emit(
            DivisionCreated(
                UUID.randomUUID(),
                Date(),
                "WARD",
                wardInfo.divisionId
            )
        )
        return true
    }

    override fun createClinicDivision(clinicInfo: CreateDivisionDTO): Boolean {
        val type: FacilityType = FacilityType.Clinic
        type.setDivisionID(clinicInfo.divisionId)
        type.setDivisionName(clinicInfo.divisionName)
        val existAccount = facilityRepository.find(type)
        if (existAccount != null){
            return false
        }
        val facility = facilityFactory.createDivision(clinicInfo)
        facilityRepository.save(facility)
        eventEmitter.emit(
            DivisionCreated(
                UUID.randomUUID(),
                Date(),
                "CLINIC",
                clinicInfo.divisionId
            )
        )
        return true
    }

    override fun updateDivision(divisionType: FacilityType, divisionInfo: CreateDivisionDTO): Boolean {
        val division = facilityRepository.find(divisionType)
        if (division != null) {
            val updated = facilityFactory.createDivision(divisionInfo)
            division.update(updated)
            facilityRepository.save(division)

            eventEmitter.emit(
                DivisionUpdated(
                    UUID.randomUUID(),
                    Date(),
                    if (divisionType == FacilityType.Clinic) "CLINIC" else "WARD",
                    divisionInfo.divisionId
                )
            )
            return true
        }
        return false
    }

    override fun getDivision(divisionType: FacilityType): Division? {
        return facilityRepository.find(divisionType)
    }

    override fun addAvailableBed(divisionType: FacilityType): Boolean {
        val division = facilityRepository.find(divisionType)
        if (division != null) {
            val success = division.addAvailableBed()
            facilityRepository.save(division)

            if(success){
                eventEmitter.emit(
                    DivisionBedAdded(
                        UUID.randomUUID(),
                        Date(),
                        if (divisionType == FacilityType.Clinic) "CLINIC" else "WARD",
                        divisionType.facilityDivisionID
                    )
                )
            }
            return true
        }
        return false
    }

    override fun removeAvailableBed(divisionType: FacilityType): Boolean {
        val division = facilityRepository.find(divisionType)
        if (division != null) {
            val success = division.removeAvailableBed()
            facilityRepository.save(division)

            if(success){
                eventEmitter.emit(
                    DivisionBedRemoved(
                        UUID.randomUUID(),
                        Date(),
                        if (divisionType == FacilityType.Clinic) "CLINIC" else "WARD",
                        divisionType.facilityDivisionID
                    )
                )
            }
            return true
        }
        return false
    }

    override fun isFull(divisionType: FacilityType): Boolean {
        val division = facilityRepository.find(divisionType)
        if (division != null) {
            return division.isFull()
        }
        return true // Assume Full If Error.
    }

    override fun addShift(staffShiftInfo: StaffShiftDTO): Boolean {
        val shift = shiftFactory.createShift(staffShiftInfo)
        val existAccount = shiftRepository.find(shift)
        if (existAccount != null){
            return false
        }
        shift.division.addShift(shift.staffNumber, shift.shiftType)
        shiftRepository.save(shift)
        eventEmitter.emit(
            ShiftAdded(
                UUID.randomUUID(),
                Date(),
                shift.staffNumber
            )
        )
        return true
    }

    override fun removeShift(staffShiftInfo: StaffShiftDTO): Boolean {
        val shift = shiftFactory.createShift(staffShiftInfo)
        val existAccount = shiftRepository.find(shift)
        if (existAccount != null){
            shift.division.removeShift(shift.staffNumber, shift.shiftType)
            shiftRepository.save(shift)
            eventEmitter.emit(
                ShiftRemoved(
                    UUID.randomUUID(),
                    Date(),
                    shift.staffNumber
                )
            )
            return true
        }
        return false
    }

    override fun getShifts(staffNumber: String): List<Shift> {
        return shiftRepository.findAll(staffNumber)
    }

}