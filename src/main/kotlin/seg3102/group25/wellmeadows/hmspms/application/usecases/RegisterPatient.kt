package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO

// Define an interface for registering patients
interface RegisterPatient {
    fun registerPatient(staffNumber: String, registerPatientInfo: RegisterPatientDTO): Boolean
}