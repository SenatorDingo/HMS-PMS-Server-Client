package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.AdmitPatientRequestListForm


@Controller
class WebAdmitPatientRequestListController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade
    @Autowired
    lateinit var patientFacade: PatientFacade
    @Autowired
    lateinit var facilityFacade: FacilityFacade



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


        //TODO: Implement Direct Call
        val facilityCaller = FacilityType.Ward
        facilityCaller.setDivisionID(admitPatientRequestListForm.divisionID!!)
        val facility = facilityFacade.getAdmissionWaitList(facilityCaller)
        val successLoadFacility = (facility != null)


        if (successLoadFacility) {

                model.addAttribute("patientList", facility)
                model.addAttribute("successMessageVisualize", "Division Visualized")

        } else {

            model.addAttribute("errorMessage", "Patient Admit From Request List Unsuccessful!") // Set error message

        }

        return "actions/ActionAdmitPatientRequestList"
    }

    @PostMapping("/actions/admit-patient-request-list/admit")
    fun admitPatient(@RequestParam("patientId") patientId: String?, @RequestParam("divisionId") divisionId: String?): String? {
        val facilityCaller = FacilityType.Ward
        facilityCaller.setDivisionID(divisionId ?: "null")

        // TODO: Doesn't actually remove - needs to be fixed
        facilityFacade.removeAdmissionWaitList(facilityCaller, patientId ?: "null")

        return "redirect:/actions/admit-patient" // Redirect to a success page after admission
    }

}