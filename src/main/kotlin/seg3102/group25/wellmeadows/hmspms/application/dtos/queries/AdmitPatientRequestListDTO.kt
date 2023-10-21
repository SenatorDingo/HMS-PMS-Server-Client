package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

data class AdmitPatientRequestListDTO(
    var patientId: String,
    var chargeNurseId: String,
    var division: String,
    var admissionStatus: String,
)