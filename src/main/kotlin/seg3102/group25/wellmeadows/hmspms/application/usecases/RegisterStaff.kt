package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO

// Define an interface for registering staff members
interface RegisterStaff {
    fun registerStaff(staffNumber: String, registerStaffInfo: RegisterStaffDTO): Boolean
}