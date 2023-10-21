package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

data class RequestPatientAdmissionDTO(
    var chargeNurseId: String,
    var patientId: String,
    var divisionId: String,
    var rationaleForRequest: String,
    var priorityAssessment: Int, // on a scale of 1 to 10
    var localDoctor: String
)