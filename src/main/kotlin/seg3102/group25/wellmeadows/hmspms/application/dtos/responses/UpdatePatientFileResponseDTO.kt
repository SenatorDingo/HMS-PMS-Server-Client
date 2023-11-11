package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class UpdatePatientFileResponseDTO(
    var success: Boolean,
    var message: String,
    var patientId: String?,
    var updatedFileDetails: PatientFileDTO?
)
