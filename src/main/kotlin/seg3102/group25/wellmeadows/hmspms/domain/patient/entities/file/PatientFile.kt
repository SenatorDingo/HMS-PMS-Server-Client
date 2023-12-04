package seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file

import com.google.cloud.firestore.annotation.Exclude
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.UpdatePatientFileDTO
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PatientFile(
    var patientNumber: String,
    var medicalStaffId: String,
    var insuranceNumber: String,
    var firstName: String,
    var lastName: String,
    var address: String,
    var telephoneNumber: String,
    var dateOfBirth: String,
    var gender: String,
    var maritalStatus: String,
    var externalDoctorId: String,
    var constituentFile: ConstituentFile // Next of Kin
) {

    var prescriptions: MutableList<PatientPrescription> = mutableListOf()
    var admitted: Boolean = false
    var division: FacilityDivision? = null
    var localDoctor: String? = null
    var roomNumber: String? = null
    var bedNumber: String? = null
    var privateInsuranceNumber: String? = null

    fun update(updated: UpdatePatientFileDTO){
        this.patientNumber = updated.patientId
        this.medicalStaffId = updated.medicalStaffId
        this.insuranceNumber = if(updated.insuranceNumber != null) updated.insuranceNumber!! else this.insuranceNumber
        this.firstName = if(updated.firstName != null) updated.firstName!! else this.firstName
        this.lastName = if(updated.lastName != null) updated.lastName!! else this.lastName
        this.address = if(updated.address != null) updated.address!! else this.address
        this.telephoneNumber = if(updated.telephoneNumber != null) updated.telephoneNumber!! else this.telephoneNumber
        this.dateOfBirth = if(updated.dateOfBirth != null) updated.dateOfBirth!! else this.dateOfBirth
        this.gender = if(updated.gender != null) updated.gender!! else this.gender
        this.maritalStatus = if(updated.maritalStatus != null) updated.maritalStatus!! else this.maritalStatus
        this.externalDoctorId = if(updated.externalDoctorId != null) updated.externalDoctorId!! else this.externalDoctorId
        this.constituentFile.update(ConstituentFile(
                this.constituentFile.constituentID,
            if(updated.nextOfKinFirstName != null) updated.nextOfKinFirstName!! else this.constituentFile.firstName,
            if(updated.nextOfKinLastName != null) updated.nextOfKinLastName!! else this.constituentFile.lastName,
            if(updated.nextOfKinAddress != null) updated.nextOfKinAddress!! else this.constituentFile.address,
            if(updated.nextOfKinTelephoneNumber != null) updated.nextOfKinTelephoneNumber!! else this.constituentFile.telephoneNumber,
            if(updated.nextOfKinRelationshipToPatient != null) updated.nextOfKinRelationshipToPatient!! else this.constituentFile.relationship,
            ))
    }

    fun admit(localDoctor: String, roomNumber: String, bedNumber: String, privateInsuranceNumber: String?, division: FacilityDivision) {
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

    fun getFormattedDateOfBirth(): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
        return dateOfBirth.format(formatter)
    }

}