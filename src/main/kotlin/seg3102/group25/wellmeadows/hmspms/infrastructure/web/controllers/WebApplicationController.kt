package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers

import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.services.principles.StaffAccountPrinciple

@Controller
class WebApplicationController {
    @Value("classpath:static/images/defaultImage.jpg")
    lateinit var defaultImgResource: Resource

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

        if (staffAccount != null) {
            model.addAttribute("roles", staffAccount.type)
        }

        return "home"
    }

    @GetMapping("/login")
    fun login(model: Model, session: HttpSession): String {
        return "login"
    }
}