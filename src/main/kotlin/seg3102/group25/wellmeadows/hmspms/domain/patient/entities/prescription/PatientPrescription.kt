package seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription

import seg3102.group25.wellmeadows.hmspms.domain.patient.valueObjects.PrescriptionType
import java.util.*

class PatientPrescription(
    var createdOn: String,
    var updatedLast: String,
    var prescriptionID: String,
    var prescriptionType: PrescriptionType,
    var doctorId: String,
    var patientId: String,
    var drugNumber: String,
    var drugName: String,
    var unitsPerDay: Int,
    var unitsAtAdministrationTimes: Int,
    var methodOfAdministration: String,
    var startDate: String,
    var finishDate: String
){
    fun update(updated: PatientPrescription){
        this.unitsPerDay = updated.unitsPerDay
        this.unitsAtAdministrationTimes = updated.unitsAtAdministrationTimes
        this.methodOfAdministration = updated.methodOfAdministration
        this.startDate = updated.startDate
        this.finishDate = updated.finishDate
        this.prescriptionType = updated.prescriptionType
        this.doctorId = updated.doctorId
        this.patientId = updated.patientId
        this.drugNumber = updated.drugNumber
        this.drugName = updated.drugName

        updatedLast = Date().toString()
    }
}