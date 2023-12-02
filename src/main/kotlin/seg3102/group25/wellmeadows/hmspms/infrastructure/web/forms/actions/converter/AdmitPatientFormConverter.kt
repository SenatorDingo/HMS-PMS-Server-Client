package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.AdmitPatientForm

class AdmitPatientFormConverter {
    companion object{
        fun convertForm(admitPatientForm: AdmitPatientForm): AdmitPatientDTO{
            return AdmitPatientDTO(admitPatientForm.patientNumber!!,
                    admitPatientForm.localDoctor!!,
                    admitPatientForm.roomNumber!!,
                    admitPatientForm.bedNumber!!,
                    admitPatientForm.privateInsuranceNumber)
        }

    }
}