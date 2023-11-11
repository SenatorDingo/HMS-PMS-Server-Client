package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

data class AdmitPatientDTO(
    var patientNumber: String,
    var localDoctor: String,
    var roomNumber: String,
    var bedNumber: String,
    var privateInsuranceNumber: String? = null
)