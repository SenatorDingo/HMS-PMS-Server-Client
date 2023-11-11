package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RequestPatientAdmissionDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.RequestPatientAdmission
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.isLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class RequestPatientAdmissionImpl(
    private var securityFacade: SecurityFacade,
    private var patientFacade: PatientFacade,
    private var facilityFacade: FacilityFacade
): RequestPatientAdmission {

    val security: Security = Security(AccessLevels.RequestPatientAdmission)
    override fun requestAdmission(staffNumber: String, requestPatientAdmissionInfo: RequestPatientAdmissionDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security) && isLoggedIn(staffNumber)){
            return facilityFacade.createAdmissionWaitList(requestPatientAdmissionInfo)
        }
        return false
    }
}