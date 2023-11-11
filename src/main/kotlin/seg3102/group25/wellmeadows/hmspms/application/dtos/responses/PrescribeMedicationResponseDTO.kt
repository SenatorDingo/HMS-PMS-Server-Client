package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class PrescribeMedicationResponseDTO(
    var success: Boolean,
    var message: String,
    var prescriptionId: String?,
    var patientId: String,
    var medicationDetails: MedicationDetailsDTO?
)

data class MedicationDetailsDTO(
    var medicationName: String,
    var dosage: String,
    var frequency: String
)
