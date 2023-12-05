package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.RegisterStaffForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.StaffShiftForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.RegisterStaffFormConverter
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.StaffShiftFormConverter

@Controller
@SessionAttributes("staffAccount")
class WebRegisterStaffController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @Autowired
    lateinit var staffAccountRepository: StaffAccountRepository


    @RequestMapping("/actions/register-staff")
    fun actionRegisterPatient(model: Model): String {

        val registerStaffForm = RegisterStaffForm()
        model.addAttribute("registerStaffForm", registerStaffForm)

        return "actions/ActionRegisterStaff"
    }

    @PostMapping("/actions/register-staff")
    fun actionRegisterPatientPost(@ModelAttribute("registerStaffForm") registerStaffForm: RegisterStaffForm,  model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = RegisterStaffFormConverter.convertForm(registerStaffForm)
        var success = patientManagementFacade.requestRegisterStaff(employeeID, dto)

        /* DIRECT CALL - SAD */
        if(success)
            success = success && staffAccountRepository.saveRoles(registerStaffForm.employeeNumber!!, registerStaffForm.role)

        if (success) {

            val staffShiftForm = StaffShiftForm()
            model.addAttribute("staffShiftForm", staffShiftForm)

            model.addAttribute("successMessage", "Staff Registration Successful!") // Set success message

        } else {

            model.addAttribute("errorMessage", "Staff Registration Unsuccessful!") // Set error message

        }

        return "actions/ActionRegisterStaff"
    }

    @PostMapping("/actions/register-staff/shift")
    fun actionRegisterPatientPost(@ModelAttribute("staffShiftForm") staffShiftForm: StaffShiftForm,  model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val dto = StaffShiftFormConverter.convertForm(staffShiftForm)
        var success = patientManagementFacade.requestUpdateStaffShift(employeeID, dto)
        /* DIRECT CALL - SAD */
        //if(success)
            success = success && staffAccountRepository.saveDivisions(staffShiftForm)

        if (success) {

            model.addAttribute("successMessageShift", "Staff Shift Added Successful!") // Set success message

        } else {

            model.addAttribute("errorMessageShift", "Staff Shift Added Unsuccessful!") // Set error message

        }

        return "actions/ActionRegisterStaff"
    }
}