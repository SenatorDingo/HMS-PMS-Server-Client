package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.factories.StaffAccountFactory

class StaffAccountDtoFactory: StaffAccountFactory {
    override fun createStaffAccount(registerStaffDTO: RegisterStaffDTO): StaffAccount {
        TODO("Not yet implemented")
    }
}