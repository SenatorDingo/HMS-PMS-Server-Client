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
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.DischargePatientForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.DischargePatientFormConverter

@Controller
class WebDischargePatientController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade


    @RequestMapping("/actions/discharge-patient")
    fun actionDischargePatient(model: Model): String {

        val dischargePatientForm = DischargePatientForm()
        model.addAttribute(dischargePatientForm)

        return "actions/ActionDischargePatient"
    }

    @PostMapping("/actions/discharge-patient")
    fun actionDischargePatientPost(@ModelAttribute("dischargePatientForm") dischargePatientForm: DischargePatientForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = DischargePatientFormConverter.convertForm(dischargePatientForm)
        val success = patientManagementFacade.requestDischargePatient(employeeID, dto, dischargePatientForm.divisionId!!)

        if (success) {

            model.addAttribute("successMessage", "Patient Discharge Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Patient Discharge Unsuccessful!") // Set error message

        }

        return "actions/ActionDischargePatient"
    }

}