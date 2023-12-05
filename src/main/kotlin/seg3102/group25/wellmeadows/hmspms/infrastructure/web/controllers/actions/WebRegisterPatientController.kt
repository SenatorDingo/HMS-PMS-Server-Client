package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.apache.catalina.core.ApplicationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.RegisterPatientForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.RegisterPatientFormConverter

@Controller
class WebRegisterPatientController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @RequestMapping("/actions/register-patient")
    fun actionRegisterPatient(model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val roles = authentication.authorities.map { it.authority } // Retrieve roles

        println("Roles allowed to access /actions/register-patient: $roles") // Print roles to console

        val registerPatientForm = RegisterPatientForm()
        model.addAttribute(registerPatientForm)

        return "actions/ActionRegisterPatient"
    }

    @PostMapping("/actions/register-patient")
    fun actionRegisterPatientPost(@ModelAttribute("registerPatientForm") registerPatientForm: RegisterPatientForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = RegisterPatientFormConverter.convertForm(registerPatientForm)
        val success = patientManagementFacade.requestRegisterPatient(employeeID, dto)

        if (success) {

            model.addAttribute("successMessage", "Patient Registration Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Patient Registration Unsuccessful!") // Set error message

        }

        return "actions/ActionRegisterPatient"
    }

}