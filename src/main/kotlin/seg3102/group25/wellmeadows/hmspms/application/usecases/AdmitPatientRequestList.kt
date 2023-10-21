package seg3102.group25.wellmeadows.hmspms.application.usecases

// Define an interface for admitting patients from the waiting list
interface AdmitPatientRequestList {
    fun admitPatientFromRequestList(
            patientId: String,
            divisionId: String,
            roomNumber: String,
            bedNumber: String,
            additionalInfo: String
    ): String
}