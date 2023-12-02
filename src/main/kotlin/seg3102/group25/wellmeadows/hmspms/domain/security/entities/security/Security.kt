package seg3102.group25.wellmeadows.hmspms.domain.security.entities.security

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType

class Security(
    private val accessLevel: AccessLevels
) {

    companion object {
        val logger: MutableList<DomainEvent> = ArrayList()
        private val staffLoggedIn: MutableList<String> = ArrayList() // List of staff Numbers


        // Won't be used by domain
        fun getEventLogs(): List<DomainEvent>{
            return logger
        }

        fun logOut(employeeNumber: String): Boolean{
            if(staffLoggedIn.contains(employeeNumber)) {
                staffLoggedIn.remove(employeeNumber)
            }
            return !isLoggedIn(employeeNumber)
        }

        fun logIn(staffAccount: StaffAccount, password: String): Boolean{
            if(!staffLoggedIn.contains(staffAccount.employeeNumber) && staffAccount.checkPassword(password)) {
                staffLoggedIn.add(staffAccount.employeeNumber)
            }
            return isLoggedIn(staffAccount.employeeNumber)
        }

        fun isLoggedIn(employeeNumber: String): Boolean{
            //return staffLoggedIn.contains(employeeNumber)
            return true
        }
    }

    fun checkAccess(staffTypes: List<StaffType>): Boolean {
        var access = false
        for (staffType in staffTypes)
            access = access || accessLevel.staffType.contains(staffType)

        return access
    }

    fun logEvent(event: DomainEvent) {
        logger.add(event)
    }
}