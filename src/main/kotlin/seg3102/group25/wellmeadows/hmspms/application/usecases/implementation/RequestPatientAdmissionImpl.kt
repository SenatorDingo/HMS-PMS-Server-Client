package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.usecases.RequestPatientAdmission
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class RequestPatientAdmissionImpl: RequestPatientAdmission {

    val security: Security = Security(AccessLevels.RequestPatientAdmission)
    override fun requestAdmission(
        patientId: String,
        divisionId: String,
        rationale: String,
        priority: Int,
        localDoctor: String
    ): String {
        TODO("Not yet implemented")
    }
}