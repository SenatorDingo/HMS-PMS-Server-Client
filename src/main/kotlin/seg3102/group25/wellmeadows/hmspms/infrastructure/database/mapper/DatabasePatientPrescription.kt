package seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper

import com.google.firebase.database.PropertyName
import seg3102.group25.wellmeadows.hmspms.domain.patient.valueObjects.PrescriptionType

class DatabasePatientPrescription(
        @get:PropertyName("createdOn")
        @set:PropertyName("createdOn")
        var createdOn: String? = null,

        @get:PropertyName("updatedLast")
        @set:PropertyName("updatedLast")
        var updatedLast: String? = null,

        @get:PropertyName("prescriptionID")
        @set:PropertyName("prescriptionID")
        var prescriptionID: String? = null,

        @get:PropertyName("prescriptionType")
        @set:PropertyName("prescriptionType")
        var prescriptionType: PrescriptionType? = null,

        @get:PropertyName("doctorId")
        @set:PropertyName("doctorId")
        var doctorId: String? = null,

        @get:PropertyName("patientId")
        @set:PropertyName("patientId")
        var patientId: String? = null,

        @get:PropertyName("drugNumber")
        @set:PropertyName("drugNumber")
        var drugNumber: String? = null,

        @get:PropertyName("drugName")
        @set:PropertyName("drugName")
        var drugName: String? = null,

        @get:PropertyName("unitsPerDay")
        @set:PropertyName("unitsPerDay")
        var unitsPerDay: Int? = null,


        @get:PropertyName("unitsAtAdministrationTimes")
        @set:PropertyName("unitsAtAdministrationTimes")
        var unitsAtAdministrationTimes: Int? = null,

        @get:PropertyName("methodOfAdministration")
        @set:PropertyName("methodOfAdministration")
        var methodOfAdministration: String? = null,

        @get:PropertyName("startDate")
        @set:PropertyName("startDate")
        var startDate: String? = null,

        @get:PropertyName("finishDate")
        @set:PropertyName("finishDate")
        var finishDate: String? = null
)
