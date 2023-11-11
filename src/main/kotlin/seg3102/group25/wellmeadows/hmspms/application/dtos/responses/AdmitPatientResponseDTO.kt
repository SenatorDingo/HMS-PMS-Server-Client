package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class AdmitPatientResponseDTO(
    var success: Boolean,
    var message: String,
    var admittedPatientNumber: String?,
    var assignedNurse: String?
)
