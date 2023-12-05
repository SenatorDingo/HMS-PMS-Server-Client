package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.VisualizeDivisionForm

@Controller
class WebVisualizeDivisionController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @RequestMapping("/actions/visualize-division")
    fun actionVisualizeDivision(model: Model): String {

        val visualizeDivisionForm = VisualizeDivisionForm()
        model.addAttribute(visualizeDivisionForm)

        return "actions/ActionVisualizeDivision"
    }

    @PostMapping("/actions/visualize-division")
    fun actionVisualizeDivisionPost(@ModelAttribute("visualizeDivisionForm") visualizeDivisionForm: VisualizeDivisionForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        //TODO: Implement Direct Calls
        /*

        val dto = VisualizeDivisionFormConverter.convertForm(visualizeDivisionForm)
        val success = patientManagementFacade.requestVisualizeDivision(employeeID, dto)

        if (success) {

            model.addAttribute("successMessage", "Patient Registration Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Patient Registration Unsuccessful!") // Set error message

        }

         */

        return "actions/ActionVisualizeDivision"
    }
}