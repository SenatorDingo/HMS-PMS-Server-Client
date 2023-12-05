package seg3102.group25.wellmeadows.hmspms.domain.staff.repositories

import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.StaffShiftForm

interface StaffAccountRepository {
    suspend fun find(employeeNumber: String): StaffAccount?
    fun save(staffAccount: StaffAccount): StaffAccount
    fun findSync(employeeNumber: String): StaffAccount?
    fun saveRoles(employeeID: String, roles: StaffType?): Boolean
    fun saveDivisions(staffShiftForm: StaffShiftForm): Boolean
}