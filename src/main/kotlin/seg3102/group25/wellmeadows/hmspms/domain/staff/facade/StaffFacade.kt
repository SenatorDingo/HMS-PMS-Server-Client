package seg3102.group25.wellmeadows.hmspms.domain.staff.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.RegisterStaff
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.Role

interface StaffFacade {
    fun createStaffAccount(accountInfo: RegisterStaffDTO): Boolean
    fun updateStaffAccount(employeeNumber: String, accountInfo: RegisterStaffDTO): Boolean
    fun updatePassword(employeeNumber: String, password: String): Boolean
    fun addRole(role: Role)
    fun removeRole(role: Role)
    fun updateRoles(roles: List<Role>)
    fun getRoles(employeeNumber: String): List<Role>
    fun addFacilityID(facilityID: String)
    fun removeFacilityID(facilityID: String)
    fun updateFacilityIDs(facilityIDs: List<String>)
    fun getFacilityIDs(employeeNumber: String): List<String>
    fun getStaffAccount(employeeNumber: String): StaffAccount?
    fun deactivateAccount(employeeNumber: String): Boolean
}