package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers

import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.services.principles.StaffAccountPrinciple

@Controller
class WebApplicationController {

    @Value("classpath:static/images/defaultImage.jpg")
    lateinit var defaultImgResource: Resource

    @Autowired
    lateinit var superRole: Array<String>

    @RequestMapping("/")
    fun showWelcome(model: Model, session: HttpSession): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val customPrincipal = authentication.principal as? StaffAccountPrinciple
        var staffAccount: StaffAccount? = null
        if (customPrincipal != null) {
            staffAccount = customPrincipal.account
            // Access properties or methods of your custom object
        } else {
            // The custom object might not be attached to this user
        }

        val roles: Array<String> = authentication.authorities.map { it.authority }.toTypedArray() // Retrieve roles
        val superRoles: Array<String> = superRole.map { "ROLE_$it" }.toTypedArray() // Retrieve super roles

        if (staffAccount != null) {
            if(staffAccount.type.any { AccessLevels.VisualizeDivision.staffType.contains(it) })
                model.addAttribute("visualizeDivision", true)
            if(staffAccount.type.any { AccessLevels.UpdatePatientFile.staffType.contains(it) })
                model.addAttribute("updatePatientFile", true)
            if(staffAccount.type.any { AccessLevels.RequestPatientAdmission.staffType.contains(it) })
                model.addAttribute("requestPatientAdmission", true)
            if(staffAccount.type.any { AccessLevels.RegisterStaff.staffType.contains(it) })
                model.addAttribute("registerStaff", true)
            if(staffAccount.type.any { AccessLevels.RegisterPatient.staffType.contains(it) })
                model.addAttribute("registerPatient", true)
            if(staffAccount.type.any { AccessLevels.PrescribeMedication.staffType.contains(it) })
                model.addAttribute("prescribeMedication", true)
            if(staffAccount.type.any { AccessLevels.DischargePatient.staffType.contains(it) })
                model.addAttribute("dischargePatient", true)
            if(staffAccount.type.any { AccessLevels.ConsultPatientFile.staffType.contains(it) })
                model.addAttribute("consultPatientFile", true)
            if(staffAccount.type.any { AccessLevels.AdmitPatient.staffType.contains(it) })
                model.addAttribute("admitPatient", true)
            if(staffAccount.type.any { AccessLevels.AdmitPatientRequestList.staffType.contains(it) })
                model.addAttribute("admitPatientFromRequestList", true)
        }
        else if((superRoles.any { roles.contains(it) })){
                model.addAttribute("visualizeDivision", true)
                model.addAttribute("updatePatientFile", true)
                model.addAttribute("requestPatientAdmission", true)
                model.addAttribute("registerStaff", true)
                model.addAttribute("registerPatient", true)
                model.addAttribute("prescribeMedication", true)
                model.addAttribute("dischargePatient", true)
                model.addAttribute("consultPatientFile", true)
                model.addAttribute("admitPatient", true)
                model.addAttribute("admitPatientFromRequestList", true)
        }

        return "home"
    }

    @GetMapping("/login")
    fun login(model: Model, session: HttpSession): String {
        return "login"
    }
}