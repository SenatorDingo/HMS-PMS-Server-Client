package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.usecases.AdmitPatient
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class AdmitPatientImpl: AdmitPatient {

    val security: Security = Security(AccessLevels.AdmitPatient)
    override fun admitPatient(
        patientId: String,
        divisionId: String,
        localDoctor: String,
        roomNumber: String,
        bedNumber: String,
        optionalPrivateInsuranceNumber: String
    ): Boolean {
        TODO("Not yet implemented")
    }
}