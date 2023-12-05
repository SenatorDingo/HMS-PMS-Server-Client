package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers.actions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade

@Controller
class WebUpdatePatientFileController {

    @Autowired
    lateinit var patientManagementFacade: PatientManagementFacade

}