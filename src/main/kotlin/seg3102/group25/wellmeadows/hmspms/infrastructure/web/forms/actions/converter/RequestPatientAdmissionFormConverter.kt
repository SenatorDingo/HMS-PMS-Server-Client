package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RequestPatientAdmissionDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.RequestPatientAdmissionForm

class RequestPatientAdmissionFormConverter {
    companion object {
        fun convertForm(requestPatientAdmissionForm: RequestPatientAdmissionForm): RequestPatientAdmissionDTO {
            return RequestPatientAdmissionDTO(
                    requestPatientAdmissionForm.chargeNurseId!!,
                    requestPatientAdmissionForm.patientId!!,
                    requestPatientAdmissionForm.divisionId!!,
                    requestPatientAdmissionForm.rationaleForRequest!!,
                    requestPatientAdmissionForm.priorityAssessment!!,
                    requestPatientAdmissionForm.localDoctor!!
            )
        }
    }
}
