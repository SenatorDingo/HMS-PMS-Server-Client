package seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.UpdatePatientFileDTO
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription

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

    fun update(updated: UpdatePatientFileDTO) {
        this.patientNumber = updated.patientId
        this.medicalStaffId = updated.medicalStaffId
        this.insuranceNumber = if (!updated.insuranceNumber.isNullOrEmpty()) updated.insuranceNumber!! else this.insuranceNumber
        this.firstName = if (!updated.firstName.isNullOrEmpty()) updated.firstName!! else this.firstName
        this.lastName = if (!updated.lastName.isNullOrEmpty()) updated.lastName!! else this.lastName
        this.address = if (!updated.address.isNullOrEmpty()) updated.address!! else this.address
        this.telephoneNumber = if (!updated.telephoneNumber.isNullOrEmpty()) updated.telephoneNumber!! else this.telephoneNumber
        this.dateOfBirth = if (!updated.dateOfBirth.isNullOrEmpty()) updated.dateOfBirth!! else this.dateOfBirth
        this.gender = if (!updated.gender.isNullOrEmpty()) updated.gender!! else this.gender
        this.maritalStatus = if (!updated.maritalStatus.isNullOrEmpty()) updated.maritalStatus!! else this.maritalStatus
        this.externalDoctorId = if (!updated.externalDoctorId.isNullOrEmpty()) updated.externalDoctorId!! else this.externalDoctorId

        this.constituentFile.update(ConstituentFile(
                this.constituentFile.constituentID,
                if (!updated.nextOfKinFirstName.isNullOrEmpty()) updated.nextOfKinFirstName!! else this.constituentFile.firstName,
                if (!updated.nextOfKinLastName.isNullOrEmpty()) updated.nextOfKinLastName!! else this.constituentFile.lastName,
                if (!updated.nextOfKinAddress.isNullOrEmpty()) updated.nextOfKinAddress!! else this.constituentFile.address,
                if (!updated.nextOfKinTelephoneNumber.isNullOrEmpty()) updated.nextOfKinTelephoneNumber!! else this.constituentFile.telephoneNumber,
                if (!updated.nextOfKinRelationshipToPatient.isNullOrEmpty()) updated.nextOfKinRelationshipToPatient!! else this.constituentFile.relationship
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

}