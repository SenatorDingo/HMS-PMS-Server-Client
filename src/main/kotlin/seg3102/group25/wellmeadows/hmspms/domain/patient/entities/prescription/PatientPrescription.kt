package seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription

import seg3102.group25.wellmeadows.hmspms.domain.patient.valueObjects.PrescriptionType
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class PatientPrescription(
    var createdOn: Date,
    var updatedLast: Date,
    var prescriptionID: String,
    var prescriptionType: PrescriptionType,
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
){

    fun update(updated: PatientPrescription){
        this.unitsPerDay = updated.unitsPerDay
        this.numberOfAdministrationsPerDay = updated.numberOfAdministrationsPerDay
        this.administrationTimes = updated.administrationTimes
        this.unitsAtAdministrationTimes = updated.unitsAtAdministrationTimes
        this.methodOfAdministration = updated.methodOfAdministration
        this.startDate = updated.startDate
        this.finishDate = updated.finishDate
        this.prescriptionType = updated.prescriptionType
        this.doctorId = updated.doctorId
        this.patientId = updated.patientId
        this.drugNumber = updated.drugNumber
        this.drugName = updated.drugName

        updatedLast = Date()
    }
}