package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO

// Define an interface for registering staff members
interface RegisterStaff {
    fun registerStaff(staffNumber: String, registerStaffInfo: RegisterStaffDTO): Boolean
    fun updateShift(staffNumber: String, updateStaffShift: StaffShiftDTO): Boolean
}