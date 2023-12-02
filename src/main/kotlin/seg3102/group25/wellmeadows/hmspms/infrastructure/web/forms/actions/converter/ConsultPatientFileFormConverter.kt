package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.ConsultPatientFileDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.ConsultPatientFileForm

class ConsultPatientFileFormConverter {
    companion object {
        fun convertForm(consultPatientFileForm: ConsultPatientFileForm): ConsultPatientFileDTO {
            return ConsultPatientFileDTO(
                    consultPatientFileForm.staffId!!,
                    consultPatientFileForm.patientId!!
            )
        }
    }
}
