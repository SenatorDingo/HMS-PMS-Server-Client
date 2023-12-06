package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.RegisterStaffForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.StaffShiftForm
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.RegisterStaffFormConverter
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter.StaffShiftFormConverter

@Controller
@SessionAttributes("staffAccount", "accountId")
class WebRegisterStaffController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

    @Autowired
    lateinit var staffAccountRepository: StaffAccountRepository


    @RequestMapping("/actions/register-staff")
    fun actionRegisterStaff(model: Model): String {
        val registerStaffForm = RegisterStaffForm()
        model.addAttribute("registerStaffForm", registerStaffForm)
        val staffShiftForm = StaffShiftForm()
        model.addAttribute("staffShiftForm", staffShiftForm)


        return "actions/ActionRegisterStaff"
    }

    @PostMapping("/actions/register-staff/register")
    fun actionRegisterStaffPost(@ModelAttribute("registerStaffForm") registerStaffForm: RegisterStaffForm, @ModelAttribute("staffShiftForm") staffShiftForm: StaffShiftForm, model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val passwordEncoder = BCryptPasswordEncoder()

        staffShiftForm.staffNumber = registerStaffForm.employeeNumber!!
        val dto1 = RegisterStaffFormConverter.convertForm(registerStaffForm)
        dto1.password = passwordEncoder.encode(dto1.password)
        val dto2 = StaffShiftFormConverter.convertForm(staffShiftForm)

        var success = patientManagementFacade.requestRegisterStaff(employeeID, dto1)

        if(success)
            success = success && patientManagementFacade.requestUpdateStaffShift(employeeID, dto2)

        /* DIRECT CALL - SAD */
        //if(success)
        success = success && staffAccountRepository.saveRoles(registerStaffForm.employeeNumber!!, registerStaffForm.role)
        success = success && staffAccountRepository.saveDivisions(staffShiftForm)

        if (success) {

            model.addAttribute("successMessage", "Staff Registration Successful!") // Set success message
        } else {

            model.addAttribute("errorMessage", "Staff Registration Unsuccessful!") // Set error message
        }

        return "actions/ActionRegisterStaff"
    }

}