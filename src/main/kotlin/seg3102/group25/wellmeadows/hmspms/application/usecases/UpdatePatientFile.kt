package seg3102.group25.wellmeadows.hmspms.application.usecases

// Define an interface for updating patient information
interface UpdatePatientFile {
    fun updatePatientInformation(
            patientId: String,
            updatedInfo: Map<String, String>
    ): Boolean
}