package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class ConsultPatientFileResponseDTO(
    var success: Boolean,
    var message: String,
    var patientFile: PatientFileDTO?
)

data class PatientFileDTO(
    var patientId: String,
    var medicalHistory: String,
    var currentTreatment: String,
)
