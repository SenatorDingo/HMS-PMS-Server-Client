package seg3102.group25.wellmeadows.hmspms.domain.staff.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.events.StaffAccountCreated
import seg3102.group25.wellmeadows.hmspms.domain.staff.facade.StaffFacade
import seg3102.group25.wellmeadows.hmspms.domain.staff.factories.StaffAccountFactory
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.Role
import java.util.*

class StaffFacadeImpl(
    private val staffAccountRepository: StaffAccountRepository,
    private val staffAccountFactory: StaffAccountFactory,
    private val eventEmitter: DomainEventEmitter
): StaffFacade {
    override fun createStaffAccount(accountInfo: RegisterStaffDTO): Boolean {
        val employeeNumber = accountInfo.employeeNumber
        val existAccount = staffAccountRepository.find(employeeNumber)
        if (existAccount != null){
            return false
        }
        val staffAccount = staffAccountFactory.createStaffAccount(accountInfo)
        staffAccountRepository.save(staffAccount)
        eventEmitter.emit(
            StaffAccountCreated(
                UUID.randomUUID(),
                Date(),
                staffAccount.employeeNumber
            )
        )
        return true
    }

    override fun updateStaffAccount(employeeNumber: String, accountInfo: RegisterStaffDTO): Boolean {
        TODO("Not yet implemented")
    }

    override fun updatePassword(employeeNumber: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun addRole(role: Role) {
        TODO("Not yet implemented")
    }

    override fun removeRole(role: Role) {
        TODO("Not yet implemented")
    }

    override fun updateRoles(roles: List<Role>) {
        TODO("Not yet implemented")
    }

    override fun getRoles(employeeNumber: String): List<Role> {
        TODO("Not yet implemented")
    }

    override fun addFacilityID(facilityID: String) {
        TODO("Not yet implemented")
    }

    override fun removeFacilityID(facilityID: String) {
        TODO("Not yet implemented")
    }

    override fun updateFacilityIDs(facilityIDs: List<String>) {
        TODO("Not yet implemented")
    }

    override fun getFacilityIDs(employeeNumber: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun getStaffAccount(employeeNumber: String): StaffAccount? {
        TODO("Not yet implemented")
    }

    override fun deactivateAccount(employeeNumber: String): Boolean {
        TODO("Not yet implemented")
    }

}