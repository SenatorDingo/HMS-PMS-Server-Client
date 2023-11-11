package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class DischargePatientResponseDTO(
    var success: Boolean,
    var message: String,
    var patientId: String,
    var dischargeDate: String
)
