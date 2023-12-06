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
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.ConsultPatientFileForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.ConsultPatientFileFormConverter

@Controller
class WebConsultPatientFileController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade
    @Autowired
    lateinit var patientFacade: PatientFacade



    @RequestMapping("/actions/consult-patient-file")
    fun actionConsultPatientFile(model: Model): String {

        val consultPatientFileForm = ConsultPatientFileForm()
        model.addAttribute(consultPatientFileForm)

        return "actions/ActionConsultPatientFile"
    }

    @PostMapping("/actions/consult-patient-file")
    fun actionConsultPatientFilePost(@ModelAttribute("consultPatientFileForm") consultPatientFileForm: ConsultPatientFileForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name


        val dto = ConsultPatientFileFormConverter.convertForm(consultPatientFileForm)

        val patientFile = patientFacade.getPatientFile(dto.patientId)
        val success = (patientFile != null)

        if (success) {

            model.addAttribute("successMessage", "Patient File Load Successful!") // Set success message
            model.addAttribute("patientInfo", patientFile)

        } else {

            model.addAttribute("errorMessage", "Patient File Load Unsuccessful!") // Set error message

        }


        return "actions/ActionConsultPatientFile"
    }

}