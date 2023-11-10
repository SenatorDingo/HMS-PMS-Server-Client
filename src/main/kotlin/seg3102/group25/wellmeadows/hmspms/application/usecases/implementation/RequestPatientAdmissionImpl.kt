package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class RequestPatientAdmissionImpl {

    val security: Security = Security(AccessLevels.RequestPatientAdmission)
}