package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
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
    fun actionRegisterStaff(model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val roles = authentication.authorities.map { it.authority } // Retrieve roles

        println("Roles allowed to access /actions/register-staff: $roles") // Print roles to console

        val registerStaffForm = RegisterStaffForm()
        model.addAttribute("registerStaffForm", registerStaffForm)

        return "actions/ActionRegisterStaff"
    }

    @PostMapping("/actions/register-staff")
    fun actionRegisterStaffPost(@ModelAttribute("registerStaffForm") registerStaffForm: RegisterStaffForm,  model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        val passwordEncoder = BCryptPasswordEncoder()

        val dto = RegisterStaffFormConverter.convertForm(registerStaffForm)
        dto.password = passwordEncoder.encode(dto.password)
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
    fun actionRegisterStaffShiftPost(@ModelAttribute("staffShiftForm") staffShiftForm: StaffShiftForm,  model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val employeeID: String = authentication.name

        staffShiftForm.staffNumber = employeeID
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