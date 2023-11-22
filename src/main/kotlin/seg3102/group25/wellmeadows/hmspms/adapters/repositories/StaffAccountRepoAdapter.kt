package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository

class StaffAccountRepoAdapter: StaffAccountRepository {
    override fun find(employeeNumber: String): StaffAccount? {
        TODO("Not yet implemented")
    }

    override fun save(staffAccount: StaffAccount): StaffAccount {
        TODO("Not yet implemented")
    }
}