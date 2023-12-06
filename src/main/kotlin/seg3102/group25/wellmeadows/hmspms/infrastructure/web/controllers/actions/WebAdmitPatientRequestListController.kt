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
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.AdmitPatientRequestListForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.AdmitPatientRequestListFormConverter

@Controller
class WebAdmitPatientRequestListController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade
    @Autowired
    lateinit var patientFacade: PatientFacade



    @RequestMapping("/actions/admit-patient-request-list")
    fun actionAdmitPatientRequestList(model: Model): String {

        val admitPatientRequestListForm = AdmitPatientRequestListForm()
        model.addAttribute(admitPatientRequestListForm)

        return "actions/ActionAdmitPatientRequestList"
    }

    @PostMapping("/actions/admit-patient-request-list")
    fun actionAdmitPatientRequestListPost(@ModelAttribute("admitPatientRequestListForm") admitPatientRequestListForm: AdmitPatientRequestListForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = AdmitPatientRequestListFormConverter.convertForm(admitPatientRequestListForm)

        //TODO: Implemenet Direct Call

        /*
        val success = patientManagementFacade.requestAdmitPatientRequestList(employeeID, dto)

        if (success) {

            model.addAttribute("successMessage", "Patient Admit From Request List Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Patient Admit From Request List Unsuccessful!") // Set error message

        }

         */

        return "actions/ActionAdmitPatientRequestList"
    }

}