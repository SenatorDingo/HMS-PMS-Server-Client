package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientRequestListDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.AdmitPatientRequestList
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class AdmitPatientRequestListImpl(
    private var securityFacade: SecurityFacade,
    private var facilityFacade: FacilityFacade,
    private var patientFacade: PatientFacade
): AdmitPatientRequestList {

    val security: Security = Security(AccessLevels.AdmitPatientRequestList)
    override fun admitPatientFromRequestList(
        staffNumber: String,
        admitPatientRequestListInfo: AdmitPatientRequestListDTO
    ): Boolean {
        if(securityFacade.checkAccess(staffNumber, security)){
            TODO("Not yet implemented")
        }
        return false
    }
}