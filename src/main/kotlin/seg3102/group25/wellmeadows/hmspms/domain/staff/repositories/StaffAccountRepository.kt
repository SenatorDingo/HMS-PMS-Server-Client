package seg3102.group25.wellmeadows.hmspms.domain.staff.repositories

import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount

interface StaffAccountRepository {
    suspend fun find(employeeNumber: String): StaffAccount?
    fun save(staffAccount: StaffAccount): StaffAccount
    fun findSync(employeeNumber: String): StaffAccount?
}