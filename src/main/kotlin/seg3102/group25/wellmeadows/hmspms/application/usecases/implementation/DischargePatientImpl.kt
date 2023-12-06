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
    override fun dischargePatient(staffNumber: String, dischargePatientInfo: DischargePatientDTO, divisionID: String): Boolean {
        if(securityFacade.checkAccess(staffNumber, security) && Security.isLoggedIn(staffNumber)){

            return (patientFacade.dischargePatient(dischargePatientInfo.patientId, divisionID))
            /*
            val facilityType = FacilityType.Ward
            patientFacade.getPatientFile(dischargePatientInfo.patientId)
                ?.let {
                    it.division
                        ?.let {
                            facilityFacade.getDivision(facilityType)
                                ?.let {
                                    if(!it.removeAdmission(dischargePatientInfo.patientId))
                                        return true

                                    return it.addAvailableBed()
                                }
                        }
                }

             */
        }
        return false


    }


}