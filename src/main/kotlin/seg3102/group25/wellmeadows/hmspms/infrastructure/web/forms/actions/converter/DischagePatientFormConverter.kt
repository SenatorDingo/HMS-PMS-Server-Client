package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.DischargePatientDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.DischargePatientForm

class DischargePatientFormConverter {
    companion object {
        fun convertForm(dischargePatientForm: DischargePatientForm): DischargePatientDTO {
            return DischargePatientDTO(
                    dischargePatientForm.patientId!!
            )
        }
    }
}
