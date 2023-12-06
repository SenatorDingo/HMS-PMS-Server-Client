package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.DischargePatientDTO

// Define an interface for discharging patients from a division
interface DischargePatient {
    fun dischargePatient(staffNumber: String, dischargePatientInfo: DischargePatientDTO, divisionID: String): Boolean
}