package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class RegisterPatientResponseDTO(
    var success: Boolean,
    var message: String,
    var patientId: String?
)
