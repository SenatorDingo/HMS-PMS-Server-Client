package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.VisualizeDivisionForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.VisualizeDivisionFormConverter

@Controller
class WebVisualizeDivisionController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade
    @Autowired
    lateinit var facilityFacade: FacilityFacade

    @RequestMapping("/actions/visualize-division")
    fun actionVisualizeDivision(model: Model): String {

        val visualizeDivisionForm = VisualizeDivisionForm()
        model.addAttribute(visualizeDivisionForm)

        return "actions/ActionVisualizeDivision"
    }

    @PostMapping("/actions/visualize-division")
    fun actionVisualizeDivisionPost(@ModelAttribute("visualizeDivisionForm") visualizeDivisionForm: VisualizeDivisionForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name //Useless because of direct call - Shameful but required


        val dto = VisualizeDivisionFormConverter.convertForm(visualizeDivisionForm)
        val facilityCaller = FacilityType.Ward
        facilityCaller.setDivisionID(dto.divisionId)
        val facility = facilityFacade.getAdmissionWaitList(facilityCaller)
        val successLoadFacility = (facility != null)


        if (successLoadFacility) {

            model.addAttribute("patientList", facility)
            model.addAttribute("successMessageVisualize", "Division Visualized")
        } else {

            model.addAttribute("errorMessage", "Division Loading Unsuccessful!") // Set error message

        }



        return "actions/ActionVisualizeDivision"
    }
}