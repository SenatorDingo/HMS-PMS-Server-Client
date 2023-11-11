package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO

// Define an interface for admitting patients to a division
interface AdmitPatient {
    fun admitPatient(staffNumber: String, admitPatientInfo: AdmitPatientDTO): Boolean
}