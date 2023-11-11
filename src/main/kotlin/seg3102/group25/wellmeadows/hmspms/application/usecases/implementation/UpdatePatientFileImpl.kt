package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.usecases.UpdatePatientFile
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class UpdatePatientFileImpl: UpdatePatientFile {

    val security: Security = Security(AccessLevels.UpdatePatientFile)
    override fun updatePatientInformation(patientId: String, updatedInfo: Map<String, String>): String {
        TODO("Not yet implemented")
    }
}