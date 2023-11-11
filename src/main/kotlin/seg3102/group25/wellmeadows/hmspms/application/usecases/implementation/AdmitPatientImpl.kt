package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.AdmitPatient
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.isLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.domain.staff.facade.StaffFacade

class AdmitPatientImpl(
    private var securityFacade: SecurityFacade,
    private var patientFacade: PatientFacade,
    private var staffFacade: StaffFacade,
    private var facilityFacade: FacilityFacade
): AdmitPatient {

    val security: Security = Security(AccessLevels.AdmitPatient)
    override fun admitPatient(staffNumber: String, admitPatientInfo: AdmitPatientDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security) && isLoggedIn(staffNumber)){
            val division = staffFacade.getFacilityIDs(staffNumber).first()
            val facilityType = FacilityType.Ward
            facilityType.setDivisionID(division)
            facilityFacade.getDivision(facilityType)
                ?.let {
                    return patientFacade.admitPatient(admitPatientInfo, admitPatientInfo.patientNumber, it)
                }
        }
        return false
    }
}