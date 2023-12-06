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
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.RequestPatientAdmissionForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.RequestPatientAdmissionFormConverter

@Controller
class WebRequestPatientAdmissionController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @RequestMapping("/actions/request-patient-admission")
    fun actionRequestPatientAdmission(model: Model): String {

        val requestPatientAdmissionForm = RequestPatientAdmissionForm()
        model.addAttribute(requestPatientAdmissionForm)

        return "actions/ActionRequestPatientAdmission"
    }

    @PostMapping("/actions/request-patient-admission")
    fun actionRequestPatientAdmissionPost(@ModelAttribute("requestPatientAdmissionForm") requestPatientAdmissionForm: RequestPatientAdmissionForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = RequestPatientAdmissionFormConverter.convertForm(requestPatientAdmissionForm)

        val success = patientManagementFacade.requestPatientAdmission(employeeID, dto)

        if (success) {

            model.addAttribute("successMessage", "Patient Admission to Waitlist Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Patient Admission to Waitlist Unsuccessful!") // Set error message

        }

        return "actions/ActionRequestPatientAdmission"
    }

}