package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class RegisterStaffResponseDTO(
    var success: Boolean,
    var message: String,
    var staffId: String?
)
