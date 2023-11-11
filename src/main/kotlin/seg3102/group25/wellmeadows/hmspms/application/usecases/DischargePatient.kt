package seg3102.group25.wellmeadows.hmspms.application.usecases

// Define an interface for discharging patients from a division
interface DischargePatient {
    fun dischargePatient(
            patientId: String,
            divisionId: String
    ): Boolean
}