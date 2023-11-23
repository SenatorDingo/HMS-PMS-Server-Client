package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class WebActionsController {

    @RequestMapping("/actions/admit-patient")
    fun actionAdmitPatient(): String {
        return "actions/ActionAdmitPatient"
    }

    @RequestMapping("/actions/admit-patient-request-list")
    fun actionAdmitPatientRequestList(): String {
        return "actions/ActionAdmitPatientRequestList"
    }

    @RequestMapping("/actions/consult-patient-file")
    fun actionConsultPatientFile(): String {
        return "actions/ActionConsultPatientFile"
    }

    @RequestMapping("/actions/discharge-patient")
    fun actionDischargePatient(): String {
        return "actions/ActionDischargePatient"
    }

    @RequestMapping("/actions/prescribe-medication")
    fun actionPrescribeMedication(): String {
        return "actions/ActionPrescribeMedication"
    }

    @RequestMapping("/actions/register-patient")
    fun actionRegisterPatient(): String {
        return "actions/ActionRegisterPatient"
    }

    @RequestMapping("/actions/register-staff")
    fun actionRegisterStaff(): String {
        return "actions/ActionRegisterStaff"
    }

    @RequestMapping("/actions/request-patient-admission")
    fun actionRequestPatientAdmission(): String {
        return "actions/ActionRequestPatientAdmission"
    }

    @RequestMapping("/actions/update-patient-file")
    fun actionUpdatePatientFile(): String {
        return "actions/ActionUpdatePatientFile"
    }

    @RequestMapping("/actions/visualize-division")
    fun actionVisualizeDivision(): String {
        return "actions/ActionVisualizeDivision"
    }
}