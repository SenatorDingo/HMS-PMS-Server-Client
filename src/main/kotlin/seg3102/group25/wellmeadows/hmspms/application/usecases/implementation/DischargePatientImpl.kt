package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.usecases.DischargePatient
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class DischargePatientImpl: DischargePatient {

    val security: Security = Security(AccessLevels.DischargePatient)
    override fun dischargePatient(patientId: String, divisionId: String): Boolean {
        TODO("Not yet implemented")
    }
}