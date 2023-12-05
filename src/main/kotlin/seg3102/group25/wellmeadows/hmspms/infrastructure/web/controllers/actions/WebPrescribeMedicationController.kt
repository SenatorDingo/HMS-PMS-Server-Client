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
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.PrescribeMedicationForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.PrescribeMedicationFormConverter

@Controller
class WebPrescribeMedicationController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @RequestMapping("/actions/prescribe-medication")
    fun actionPrescribeMedication(model: Model): String {

        val prescribeMedicationForm = PrescribeMedicationForm()
        model.addAttribute(prescribeMedicationForm)

        return "actions/ActionPrescribeMedication"
    }

    @PostMapping("/actions/prescribe-medication")
    fun actionPrescribeMedicationPost(@ModelAttribute("prescribeMedicationForm") prescribeMedicationForm: PrescribeMedicationForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = PrescribeMedicationFormConverter.convertForm(prescribeMedicationForm)
        val success = patientManagementFacade.requestPrescribeMedication(employeeID, dto)

        if (success) {

            model.addAttribute("successMessage", "Prescription Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Prescription Unsuccessful!") // Set error message

        }

        return "actions/ActionPrescribeMedication"
    }
}