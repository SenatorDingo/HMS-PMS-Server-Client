package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class StaffShiftResponseDTO(
    var success: Boolean,
    var message: String,
    var shiftDetails: ShiftDetailsDTO?
)

data class ShiftDetailsDTO(
    var staffId: String,
    var shiftStartTime: String,
    var shiftEndTime: String
)
