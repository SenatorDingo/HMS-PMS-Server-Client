package seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission

data class Admission(
    var patientNumber: String,
    var localDoctor: String,
    var roomNumber: String,
    var bedNumber: String,
    var privateInsuranceNumber: String? = null
)