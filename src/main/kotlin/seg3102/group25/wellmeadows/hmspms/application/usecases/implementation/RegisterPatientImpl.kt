package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.RegisterPatient
import seg3102.group25.wellmeadows.hmspms.domain.constituent.facade.ConstituentFacade
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class RegisterPatientImpl(
    private var securityFacade: SecurityFacade,
    private var patientFacade: PatientFacade,
    private var constituentFacade: ConstituentFacade
): RegisterPatient {

    val security: Security = Security(AccessLevels.RegisterPatient)
    override fun registerPatient(staffNumber: String, registerPatientInfo: RegisterPatientDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security)){
            return patientFacade.createPatientFile(registerPatientInfo)
        }
        return false
    }
}