package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class AdmitPatientRequestListResponseDTO(
    var success: Boolean,
    var message: String,
    var admissionDetails: List<AdmissionDetailDTO>?
)

data class AdmissionDetailDTO(
    var patientId: String,
    var admissionStatus: String
)
