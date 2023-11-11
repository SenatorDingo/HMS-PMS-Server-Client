package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.usecases.RegisterStaff
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class RegisterStaffImpl: RegisterStaff {

    val security: Security = Security(AccessLevels.RegisterStaff)
    override fun registerStaff(
        employeeNumber: String,
        loginPassword: String,
        firstName: String,
        lastName: String,
        email: String
    ): Boolean {
        TODO("Not yet implemented")
    }
}