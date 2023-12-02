package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions

import java.time.LocalDate
import java.time.LocalTime

class PrescribeMedicationForm {
    var doctorId: String? = null
    var patientId: String? = null
    var drugNumber: String? = null
    var drugName: String? = null
    var unitsPerDay: Int? = null
    var numberOfAdministrationsPerDay: Int? = null
    var administrationTimes: List<LocalTime>? = null
    var unitsAtAdministrationTimes: List<Int>? = null
    var methodOfAdministration: String? = null
    var startDate: LocalDate? = null
    var finishDate: LocalDate? = null
}