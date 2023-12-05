package seg3102.group25.wellmeadows.hmspms.domain.staff.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.events.*
import seg3102.group25.wellmeadows.hmspms.domain.staff.facade.StaffFacade
import seg3102.group25.wellmeadows.hmspms.domain.staff.factories.StaffAccountFactory
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType
import java.util.*
import kotlin.collections.ArrayList

class StaffFacadeImpl(
    private val staffAccountRepository: StaffAccountRepository,
    private val staffAccountFactory: StaffAccountFactory,
    private val eventEmitter: DomainEventEmitter
): StaffFacade {
    override fun createStaffAccount(accountInfo: RegisterStaffDTO): Boolean {
        val employeeNumber = accountInfo.employeeNumber

        val existAccount = staffAccountRepository.findSync(employeeNumber)
        if (existAccount != null){
            return false
        }

        val staffAccount = staffAccountFactory.createStaffAccount(accountInfo)
        staffAccountRepository.save(staffAccount)
        eventEmitter.emit(StaffAccountCreated(
                UUID.randomUUID(),
                Date(),
                staffAccount.employeeNumber
            ))
        return true
    }

    override fun updateStaffAccount(employeeNumber: String, accountInfo: RegisterStaffDTO): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            val updated = staffAccountFactory.createStaffAccount(accountInfo)
            staffAccount.update(updated)
            staffAccountRepository.save(staffAccount)
            eventEmitter.emit(StaffAccountUpdated(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

    override fun updatePassword(employeeNumber: String, password: String): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            staffAccount.updatePassword(password)
            staffAccountRepository.save(staffAccount)
            eventEmitter.emit(PasswordUpdated(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

    override fun addRole(employeeNumber: String, type: StaffType): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            staffAccount.addType(type)
            staffAccountRepository.save(staffAccount)
            eventEmitter.emit(TypeAdded(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

    override fun removeRole(employeeNumber: String, type: StaffType): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            staffAccount.removeType(type)
            staffAccountRepository.save(staffAccount)
            eventEmitter.emit(TypeRemoved(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

    override fun updateRoles(employeeNumber: String, types: List<StaffType>): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            staffAccount.updateTypes(types)
            staffAccountRepository.save(staffAccount)
            eventEmitter.emit(TypesUpdated(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

    override fun getRoles(employeeNumber: String): List<StaffType> {
        val types = ArrayList<StaffType>()
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            types.addAll(staffAccount.type!!)
        }
        return types
    }

    override fun addFacilityID(employeeNumber: String, facilityID: String): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            staffAccount.addFacilityID(facilityID)
            staffAccountRepository.save(staffAccount)
            eventEmitter.emit(FacilityIDAdded(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

    override fun removeFacilityID(employeeNumber: String, facilityID: String): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            staffAccount.removeFacilityID(facilityID)
            staffAccountRepository.save(staffAccount)
            eventEmitter.emit(FacilityIDRemoved(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

    override fun updateFacilityIDs(employeeNumber: String, facilityIDs: List<String>): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            staffAccount.updateFacilityIDs(facilityIDs)
            staffAccountRepository.save(staffAccount)
            eventEmitter.emit(FacilityIDsUpdated(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

    override fun getFacilityIDs(employeeNumber: String): List<String> {
        val facilityID = ArrayList<String>()
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            facilityID.addAll(staffAccount.facilityID!!)
        }
        return facilityID
    }

    override fun getStaffAccount(employeeNumber: String): StaffAccount? {
        return staffAccountRepository.findSync(employeeNumber)
    }

    override fun deactivateAccount(employeeNumber: String): Boolean {
        val staffAccount = staffAccountRepository.findSync(employeeNumber)
        if (staffAccount != null) {
            staffAccount.deactivate()
            eventEmitter.emit(StaffAccountDeactivated(
                UUID.randomUUID(),
                Date(),
                employeeNumber
            ))
            return true
        }
        return false
    }

}