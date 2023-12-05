package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.PrescribeMedication
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class PrescribeMedicationImpl(
    private var securityFacade: SecurityFacade,
    private var patientFacade: PatientFacade
): PrescribeMedication {

    val security: Security = Security(AccessLevels.PrescribeMedication)
    override fun prescribeMedication(staffNumber: String, prescribeMedicationInfo: PrescribeMedicationDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security)){
            return patientFacade.createPatientPrescription(prescribeMedicationInfo)
        }
        return false
    }
}