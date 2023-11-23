package seg3102.group25.wellmeadows.hmspms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.services.FireBaseInitialize


@SpringBootApplication
class HmspmsApplication

fun main(args: Array<String>) {
	runApplication<HmspmsApplication>(*args)
}
