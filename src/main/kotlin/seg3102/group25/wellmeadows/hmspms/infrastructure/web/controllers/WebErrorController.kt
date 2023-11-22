package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers

import jakarta.servlet.http.HttpSession
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebErrorController: ErrorController {
    @GetMapping("/error")
    fun showError(model: Model, session: HttpSession): String {
       return "error"
    }
}