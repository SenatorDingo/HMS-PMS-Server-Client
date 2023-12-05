package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientRequestListDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.AdmitPatientRequestListForm

class AdmitPatientRequestListFormConverter {

    companion object{
        fun convertForm(admitPatientRequestListForm: AdmitPatientRequestListForm): AdmitPatientRequestListDTO{
            return AdmitPatientRequestListDTO(admitPatientRequestListForm.patientId!!,
                        admitPatientRequestListForm.chargeNurseId!!,
                        admitPatientRequestListForm.divisionID!!,
                        admitPatientRequestListForm.admissionStatus!!)
        }
    }
}