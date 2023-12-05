package seg3102.group25.wellmeadows.hmspms.infrastructure.web.services.principles

import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import java.security.Principal

class StaffAccountPrinciple(val account: StaffAccount?, val employeeNumber: String): Principal {
    override fun getName(): String {
        if(account != null){
            return account.employeeNumber
        }
        return employeeNumber
    }
}