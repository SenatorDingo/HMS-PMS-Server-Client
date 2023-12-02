package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions

class RequestPatientAdmissionForm {
    var chargeNurseId: String? = null
    var patientId: String? = null
    var divisionId: String? = null
    var rationaleForRequest: String? = null
    var priorityAssessment: Int? = null // on a scale of 1 to 10
    var localDoctor: String? = null
}