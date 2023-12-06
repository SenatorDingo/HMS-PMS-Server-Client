package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.AdmitPatientForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.AdmitPatientFormConverter

@Controller
class WebAdmitPatientController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @RequestMapping("/actions/admit-patient")
    fun actionAdmitPatient(model: Model): String {

        val admitPatientForm = AdmitPatientForm()
        model.addAttribute(admitPatientForm)

        return "actions/ActionAdmitPatient"
    }

    @PostMapping("/actions/admit-patient")
    fun actionAdmitPatientPost(@ModelAttribute("admitPatientForm") admitPatientForm: AdmitPatientForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = AdmitPatientFormConverter.convertForm(admitPatientForm)
        val success = patientManagementFacade.requestAdmitPatient(employeeID, dto)

        if (success) {

            model.addAttribute("successMessage", "Patient Admission Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Patient Admission Unsuccessful!") // Set error message

        }

        return "actions/ActionAdmitPatient"
    }

}