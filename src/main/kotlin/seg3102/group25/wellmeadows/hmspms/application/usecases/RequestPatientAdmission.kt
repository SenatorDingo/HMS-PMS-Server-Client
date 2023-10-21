package seg3102.group25.wellmeadows.hmspms.application.usecases

// Define an interface for requesting patient admission to a division
interface RequestPatientAdmission {
    fun requestAdmission(
            patientId: String,
            divisionId: String,
            rationale: String,
            priority: Int,
            localDoctor: String
    ): String
}