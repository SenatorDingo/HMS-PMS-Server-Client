package seg3102.group25.wellmeadows.hmspms.application.usecases

interface PerscribeMedication {
    fun prescribeMedication(
            patientId: String,
            drugNumber: String,
            drugName: String,
            unitsPerDay: Int,
            numAdminsPerDay: Int,
            adminTimes: List<String>,
            methodOfAdmin: String,
            startDate: String,
            finishDate: String
    ): String
}