package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.factories.StaffAccountFactory

class StaffAccountFactoryStub : StaffAccountFactory {
    override fun createStaffAccount(registerStaffDTO: RegisterStaffDTO): StaffAccount {
        TODO("Not yet implemented")
    }
}