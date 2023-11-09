package seg3102.group25.wellmeadows.hmspms.domain.staff.repositories

import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount

interface StaffAccountRepository {
    fun find(employeeNumber: String): StaffAccount?
    fun save(staffAccount: StaffAccount): StaffAccount
}