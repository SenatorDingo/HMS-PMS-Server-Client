package seg3102.group25.wellmeadows.hmspms.domain.staff.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount

interface StaffAccountFactory {
    fun createStaffAccount(registerStaffDTO: RegisterStaffDTO): StaffAccount
}