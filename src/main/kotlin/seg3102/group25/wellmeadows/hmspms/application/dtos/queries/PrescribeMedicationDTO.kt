package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

import java.time.LocalDate
import java.time.LocalTime

data class PrescribeMedicationDTO(
    var doctorId: String,
    var patientId: String,
    var drugNumber: String,
    var drugName: String,
    var unitsPerDay: Int,
    var numberOfAdministrationsPerDay: Int,
    var administrationTimes: List<LocalTime>,
    var unitsAtAdministrationTimes: List<Int>,
    var methodOfAdministration: String,
    var startDate: LocalDate,
    var finishDate: LocalDate
)