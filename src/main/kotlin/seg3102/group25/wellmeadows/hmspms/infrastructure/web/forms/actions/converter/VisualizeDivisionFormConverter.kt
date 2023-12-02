package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.VisualizeDivisionDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.VisualizeDivisionForm

class VisualizeDivisionFormConverter {
    companion object {
        fun convertForm(visualizeDivisionForm: VisualizeDivisionForm): VisualizeDivisionDTO {
            return VisualizeDivisionDTO(
                    chargeNurseID = visualizeDivisionForm.chargeNurseID!!,
                    divisionId = visualizeDivisionForm.divisionId!!
            )
        }
    }
}
