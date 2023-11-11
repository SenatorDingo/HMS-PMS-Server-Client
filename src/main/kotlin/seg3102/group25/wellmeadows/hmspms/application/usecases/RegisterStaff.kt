package seg3102.group25.wellmeadows.hmspms.application.usecases

// Define an interface for registering staff members
interface RegisterStaff {
    fun registerStaff(
            employeeNumber: String,
            loginPassword: String,
            firstName: String,
            lastName: String,
            email: String
    ): Boolean
}