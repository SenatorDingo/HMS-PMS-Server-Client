package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.PrescribeMedicationForm

class PrescribeMedicationFormConverter {
    companion object {
        fun convertForm(prescribeMedicationForm: PrescribeMedicationForm): PrescribeMedicationDTO {
            return PrescribeMedicationDTO(
                    prescribeMedicationForm.doctorId!!,
                    prescribeMedicationForm.patientId!!,
                    prescribeMedicationForm.drugNumber!!,
                    prescribeMedicationForm.drugName!!,
                    prescribeMedicationForm.unitsPerDay!!,
                    prescribeMedicationForm.unitsAtAdministrationTimes!!,
                    prescribeMedicationForm.methodOfAdministration!!,
                    prescribeMedicationForm.startDate!!,
                    prescribeMedicationForm.finishDate!!
            )
        }
    }
}
