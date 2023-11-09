package seg3102.group25.wellmeadows.hmspms.domain.staff.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.RegisterStaff
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.Role

interface StaffFacade {
    fun createStaffAccount(accountInfo: RegisterStaffDTO): Boolean
    fun updateStaffAccount(employeeNumber: String, accountInfo: RegisterStaffDTO): Boolean
    fun updatePassword(employeeNumber: String, password: String): Boolean
    fun addRole(employeeNumber: String, role: Role): Boolean
    fun removeRole(employeeNumber: String, role: Role): Boolean
    fun updateRoles(employeeNumber: String, roles: List<Role>): Boolean
    fun getRoles(employeeNumber: String): List<Role>
    fun addFacilityID(employeeNumber: String, facilityID: String): Boolean
    fun removeFacilityID(employeeNumber: String, facilityID: String): Boolean
    fun updateFacilityIDs(employeeNumber: String, facilityIDs: List<String>): Boolean
    fun getFacilityIDs(employeeNumber: String): List<String>
    fun getStaffAccount(employeeNumber: String): StaffAccount?
    fun deactivateAccount(employeeNumber: String): Boolean
}