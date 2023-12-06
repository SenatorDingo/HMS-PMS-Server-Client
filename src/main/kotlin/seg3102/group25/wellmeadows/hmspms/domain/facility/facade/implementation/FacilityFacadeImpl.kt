package seg3102.group25.wellmeadows.hmspms.domain.facility.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.*
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.events.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityAdmissionWaitListFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.ShiftFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityAdmissionWaitListRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.ShiftRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import java.util.*
import kotlin.collections.ArrayList

class FacilityFacadeImpl(
    private val facilityRepository: FacilityRepository,
    private val shiftRepository: ShiftRepository,
    private val admissionWaitListRepository: FacilityAdmissionWaitListRepository,
    private val facilityFactory: FacilityFactory,
    private val shiftFactory: ShiftFactory,
    private val admissionWaitListFactory: FacilityAdmissionWaitListFactory,
    private val eventEmitter: DomainEventEmitter
): FacilityFacade {
    override fun createWardDivision(wardInfo: CreateDivisionDTO): Boolean {
        val type: FacilityType = FacilityType.Ward
        type.setDivisionID(wardInfo.divisionId)
        type.setDivisionName(wardInfo.divisionName)
        val existAccount = facilityRepository.findSync(type)
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
        val existAccount = facilityRepository.findSync(type)
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
        val division = facilityRepository.findSync(divisionType)
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

    override fun getDivision(divisionType: FacilityType): FacilityDivision? {
        return facilityRepository.findSync(divisionType)
    }

    override fun addAvailableBed(divisionType: FacilityType): Boolean {
        val division = facilityRepository.findSync(divisionType)
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
        val division = facilityRepository.findSync(divisionType)
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
        val division = facilityRepository.findSync(divisionType)
        if (division != null) {
            return division.isFull()
        }
        return true // Assume Full If Error.
    }

    override fun addShift(staffShiftInfo: StaffShiftDTO): Boolean {
        val shift = shiftFactory.createShift(staffShiftInfo)
        val existAccount = shiftRepository.findSync(shift)
        if (existAccount != null){
            return false
        }
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
        val existAccount = shiftRepository.findSync(shift)
        if (existAccount != null){
            shiftRepository.remove(shift)
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

    override fun getShifts(staffNumber: String): List<FacilityShift> {
        return shiftRepository.findAll(staffNumber)
    }

    override fun getAdmissionWaitList(divisionType: FacilityType): List<FacilityAdmissionWaitList> {
        return admissionWaitListRepository.findSyncAll(divisionType.facilityDivisionID) ?: mutableListOf()
    }

    override fun createAdmissionWaitList(admissionWaitListInfo: RequestPatientAdmissionDTO): Boolean {

        val divisionType = FacilityType.Ward
        divisionType.setDivisionID(admissionWaitListInfo.divisionId)
        val division = facilityRepository.findSync(divisionType)

        val admissionWaitList = admissionWaitListFactory.createAdmissionWaitList(admissionWaitListInfo)
        val existAccount = admissionWaitListRepository.findSync(admissionWaitList)
        if (existAccount != null){
            return false
        }
        admissionWaitList.division = divisionType.facilityDivisionID
        //if(division != null){ // TODO: FutureProof
            division?.addAdmissionWaitList(admissionWaitList)
            if (division != null) {
                facilityRepository.save(division)
            }
            admissionWaitListRepository.save(admissionWaitList)
            eventEmitter.emit(
                PatientAdmissionWaitListed(
                    UUID.randomUUID(),
                    Date(),
                    admissionWaitList.patientId
                )
            )
            return true
        //}
        return false
    }

    override fun removeAdmissionWaitList(divisionType: FacilityType, patientID: String): Boolean {
        val division = facilityRepository.findSync(divisionType)
        //if(division != null){ // TODO: Futureproof
            var success = division?.removeAdmissionWaitList(patientID)
            success = admissionWaitListRepository.remove(divisionType.facilityDivisionID)
            if (division != null) {
                facilityRepository.save(division)
            }
            if(success == true) {
                eventEmitter.emit(
                    PatientAdmissionUnWaitListed(
                        UUID.randomUUID(),
                        Date(),
                        patientID
                    )
                )
                return true
            }
        //}
        return false
    }

    override fun getAdmissions(divisionType: FacilityType): List<Admission> {
        val division = facilityRepository.findSync(divisionType)
        var admissions: List<Admission> = ArrayList()
        if(division != null){
            admissions = division.admissions
        }
        return admissions
    }

    override fun addAdmission(divisionType: FacilityType, admissionInfo: AdmitPatientDTO): Boolean {
        val division = facilityRepository.findSync(divisionType)
        val admission = Admission(
            admissionInfo.patientNumber,
            admissionInfo.localDoctor,
            admissionInfo.roomNumber,
            admissionInfo.bedNumber,
            admissionInfo.privateInsuranceNumber
        )
        if(division != null){
            return division.addAdmission(admission)
        }
        return false
    }

    override fun removeAdmission(divisionType: FacilityType, patientID: String): Boolean {
        val division = facilityRepository.findSync(divisionType)
        if(division != null){
            return division.removeAdmission(patientID)
        }
        return false
    }

}
