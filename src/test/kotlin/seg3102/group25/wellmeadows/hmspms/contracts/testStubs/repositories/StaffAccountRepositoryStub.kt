package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.repositories

import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository

class StaffAccountRepositoryStub : StaffAccountRepository {
    override suspend fun find(employeeNumber: String): StaffAccount? {
        TODO("Not yet implemented")
    }

    override fun save(staffAccount: StaffAccount): StaffAccount {
        TODO("Not yet implemented")
    }

    override fun findSync(employeeNumber: String): StaffAccount? {
        TODO("Not yet implemented")
    }
}