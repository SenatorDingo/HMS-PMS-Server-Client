package seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file

import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import java.time.LocalDate

class PatientFile(
    var patientNumber: String,
    var medicalStaffId: String,
    var insuranceNumber: String,
    var firstName: String,
    var lastName: String,
    var address: String,
    var telephoneNumber: String,
    var dateOfBirth: LocalDate,
    var gender: String,
    var maritalStatus: String,
    var externalDoctorId: String,
    var constituentFile: ConstituentFile // Next of Kin
) {

    private val prescriptions: MutableList<PatientPrescription> = ArrayList()
    var admitted: Boolean = false
    var division: FacilityDivision? = null
    var localDoctor: String? = null
    var roomNumber: String? = null
    var bedNumber: String? = null
    var privateInsuranceNumber: String? = null

    fun update(updated: PatientFile){
        this.patientNumber = updated.patientNumber
        this.medicalStaffId = updated.medicalStaffId
        this.insuranceNumber = updated.insuranceNumber
        this.firstName = updated.firstName
        this.lastName = updated.lastName
        this.address = updated.address
        this.telephoneNumber = updated.telephoneNumber
        this.dateOfBirth = updated.dateOfBirth
        this.gender = updated.gender
        this.maritalStatus = updated.maritalStatus
        this.externalDoctorId = updated.externalDoctorId
        this.constituentFile = updated.constituentFile
    }

    fun admit(localDoctor: String, roomNumber: String, bedNumber: String, privateInsuranceNumber: String?, division: FacilityDivision){
        admitted = true

        this.division = division
        this.localDoctor = localDoctor
        this.roomNumber = roomNumber
        this.bedNumber = bedNumber
        this.privateInsuranceNumber = privateInsuranceNumber
    }

    fun discharge(){
        admitted = false
    }

    fun addPrescription(prescription: PatientPrescription): Boolean{
        return prescriptions.add(prescription)
    }

    fun updatePrescription(prescription: PatientPrescription): Boolean{
        if(prescriptions.find { it.prescriptionID == prescription.prescriptionID } != null) {
            prescriptions[prescriptions.indexOfFirst { it.prescriptionID == prescription.prescriptionID }] =
                prescription
            return true
        }
        return false
    }


}