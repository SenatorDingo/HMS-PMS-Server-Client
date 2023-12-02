package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.apache.catalina.core.ApplicationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
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

        val registerPatientForm: RegisterPatientForm = RegisterPatientForm()
        model.addAttribute(registerPatientForm)

        return "actions/ActionRegisterPatient"
    }

    @PostMapping("/actions/register-patient")
    fun actionRegisterPatientPost(@ModelAttribute("registerPatientForm") registerPatientForm: RegisterPatientForm): String {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User
        val employeeID: String = user.username

        val dto = RegisterPatientFormConverter.convertForm(registerPatientForm)

        patientManagementFacade.requestRegisterPatient(employeeID, dto)

        println(" Created Patient Successfully")
        return "home"
    }

}