package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

data class PrescribeMedicationDTO(
    var doctorId: String,
    var patientId: String,
    var drugNumber: String,
    var drugName: String,
    var unitsPerDay: Int,
    var unitsAtAdministrationTimes: Int,
    var methodOfAdministration: String,
    var startDate: String,
    var finishDate: String
)