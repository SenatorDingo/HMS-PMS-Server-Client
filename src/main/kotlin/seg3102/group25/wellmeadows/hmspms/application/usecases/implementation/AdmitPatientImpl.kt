package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.AdmitPatient
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.domain.staff.facade.StaffFacade

class AdmitPatientImpl(
    private var securityFacade: SecurityFacade,
    private var patientFacade: PatientFacade,
    private var staffFacade: StaffFacade
): AdmitPatient {

    val security: Security = Security(AccessLevels.AdmitPatient)
    override fun admitPatient(staffNumber: String, admitPatientInfo: AdmitPatientDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security)){
            TODO("Not yet implemented")
        }
        return false
    }
}