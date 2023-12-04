package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.UpdatePatientFileDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.UpdatePatientFile
import seg3102.group25.wellmeadows.hmspms.domain.constituent.facade.ConstituentFacade
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.isLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class UpdatePatientFileImpl(
    private var securityFacade: SecurityFacade,
    private var patientFacade: PatientFacade,
    private var constituentFacade: ConstituentFacade
): UpdatePatientFile {

    val security: Security = Security(AccessLevels.UpdatePatientFile)
    override fun updatePatientFile(staffNumber: String, updatePatientFileDTO: UpdatePatientFileDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security) && isLoggedIn(staffNumber)){
            return patientFacade.updatePatientFile(updatePatientFileDTO)
        }
        return false
    }

}