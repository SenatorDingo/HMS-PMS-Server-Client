package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.DischargePatientDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.DischargePatient
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class DischargePatientImpl(
    private var securityFacade: SecurityFacade,
    private var facilityFacade: FacilityFacade,
    private var patientFacade: PatientFacade
): DischargePatient {

    val security: Security = Security(AccessLevels.DischargePatient)
    override fun dischargePatient(staffNumber: String, dischargePatientInfo: DischargePatientDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security) && Security.isLoggedIn(staffNumber)){
            patientFacade.dischargePatient(dischargePatientInfo.patientId)
        }
        return false
    }
}