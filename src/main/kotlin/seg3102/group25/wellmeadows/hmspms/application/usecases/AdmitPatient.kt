package seg3102.group25.wellmeadows.hmspms.application.usecases

// Define an interface for admitting patients to a division
interface AdmitPatient {
    fun admitPatient(
            patientId: String,
            divisionId: String,
            localDoctor: String,
            roomNumber: String,
            bedNumber: String,
            optionalPrivateInsuranceNumber: String
    ): Boolean
}