package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class RequestPatientAdmissionResponseDTO(
    var success: Boolean,
    var message: String,
    var admissionRequestId: String?,
    var expectedAdmissionDate: String?
)
