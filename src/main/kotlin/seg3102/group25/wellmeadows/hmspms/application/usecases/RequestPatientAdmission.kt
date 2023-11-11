package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RequestPatientAdmissionDTO

// Define an interface for requesting patient admission to a division
interface RequestPatientAdmission {
    fun requestAdmission(staffNumber: String, requestPatientAdmissionInfo: RequestPatientAdmissionDTO): Boolean
}