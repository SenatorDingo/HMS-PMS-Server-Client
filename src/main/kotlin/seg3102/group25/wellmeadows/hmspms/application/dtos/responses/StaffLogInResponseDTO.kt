package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class StaffLogInResponseDTO(
    var success: Boolean,
    var message: String,
    var staffId: String?,
    var loggedInTime: String?
)

data class StaffLogOutResponseDTO(
    var success: Boolean,
    var message: String,
    var staffId: String?,
    var loggedOutTime: String?
)
