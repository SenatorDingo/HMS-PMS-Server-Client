package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers

import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
class WebApplicationController {
    @Value("classpath:static/images/defaultImage.jpg")
    lateinit var defaultImgResource: Resource

    @RequestMapping("/")
    fun showWelcome(model: Model, session: HttpSession): String {
        return "home"
    }

    @GetMapping("/login")
    fun login(model: Model, session: HttpSession): String {
        return "login"
    }
}