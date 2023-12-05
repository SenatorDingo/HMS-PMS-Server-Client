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
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.UpdatePatientFileForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.UpdatePatientFileFormConverter

@Controller
class WebUpdatePatientFileController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @RequestMapping("/actions/update-patient-file")
    fun actionUpdatePatientFile(model: Model): String {

        val updatePatientFileForm = UpdatePatientFileForm()
        model.addAttribute(updatePatientFileForm)

        return "actions/ActionUpdatePatientFile"
    }

    @PostMapping("/actions/update-patient-file")
    fun actionUpdatePatientFilePost(@ModelAttribute("updatePatientFileForm") updatePatientFileForm: UpdatePatientFileForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = UpdatePatientFileFormConverter.convertForm(updatePatientFileForm)
        val success = patientManagementFacade.requestUpdatePatientFile(employeeID, dto)

        if (success) {

            model.addAttribute("successMessage", "Patient Updated Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Patient Updated Unsuccessful!") // Set error message

        }

        return "actions/ActionUpdatePatientFile"
    }

}