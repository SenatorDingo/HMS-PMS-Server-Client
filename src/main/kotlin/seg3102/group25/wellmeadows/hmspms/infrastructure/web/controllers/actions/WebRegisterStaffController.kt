package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.RegisterStaffForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.RegisterStaffFormConverter

@Controller
class WebRegisterStaffController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @RequestMapping("/actions/register-staff")
    fun actionRegisterPatient(model: Model): String {

        val registerStaffForm = RegisterStaffForm()
        model.addAttribute(registerStaffForm)

        return "actions/ActionRegisterStaff"
    }

    @PostMapping("/actions/register-staff")
    fun actionRegisterPatientPost(@ModelAttribute("registerStaffForm") registerStaffForm: RegisterStaffForm,  model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = RegisterStaffFormConverter.convertForm(registerStaffForm)
        val success = patientManagementFacade.requestRegisterStaff(employeeID, dto)

        if (success) {

            model.addAttribute("successMessage", "Staff Registration Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Staff Registration Unsuccessful!") // Set error message

        }

        return "actions/ActionRegisterStaff"
    }
}