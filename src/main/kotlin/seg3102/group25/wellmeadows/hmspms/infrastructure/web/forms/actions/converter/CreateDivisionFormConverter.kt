package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.CreateDivisionDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.CreateDivisionForm

class CreateDivisionFormConverter {
    companion object {
        fun convertForm(createDivisionForm: CreateDivisionForm): CreateDivisionDTO {
            return CreateDivisionDTO(
                    createDivisionForm.divisionId!!,
                    createDivisionForm.divisionName!!,
                    createDivisionForm.chargeNurseFirstName!!,
                    createDivisionForm.chargeNurseLastName!!,
                    createDivisionForm.chargeNurseTelExtension!!,
                    createDivisionForm.chargeNurseBipExtension!!,
                    createDivisionForm.location!!,
                    createDivisionForm.numberBeds!!,
                    createDivisionForm.telephoneExtension!!
            )
        }
    }
}
