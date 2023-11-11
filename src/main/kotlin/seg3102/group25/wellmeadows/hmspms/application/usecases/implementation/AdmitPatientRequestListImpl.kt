package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.usecases.AdmitPatientRequestList
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class AdmitPatientRequestListImpl: AdmitPatientRequestList {

    val security: Security = Security(AccessLevels.AdmitPatientRequestList)
    override fun admitPatientFromRequestList(
        patientId: String,
        divisionId: String,
        roomNumber: String,
        bedNumber: String,
        additionalInfo: String
    ): Boolean {
        TODO("Not yet implemented")
    }
}