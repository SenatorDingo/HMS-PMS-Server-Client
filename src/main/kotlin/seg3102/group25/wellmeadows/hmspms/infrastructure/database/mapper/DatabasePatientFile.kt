package seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper

import com.google.firebase.database.PropertyName
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription

class DatabasePatientFile (
    @get:PropertyName("patientNumber")
    @set:PropertyName("patientNumber")
    var patientNumber: String? = null,

    @get:PropertyName("medicalStaffId")
    @set:PropertyName("medicalStaffId")
    var medicalStaffId: String? = null,

    @get:PropertyName("insuranceNumber")
    @set:PropertyName("insuranceNumber")
    var insuranceNumber: String? = null,

    @get:PropertyName("firstName")
    @set:PropertyName("firstName")
    var firstName: String? = null,

    @get:PropertyName("lastName")
    @set:PropertyName("lastName")
    var lastName: String? = null,

    @get:PropertyName("address")
    @set:PropertyName("address")
    var address: String? = null,

    @get:PropertyName("telephoneNumber")
    @set:PropertyName("telephoneNumber")
    var telephoneNumber: String? = null,

    @get:PropertyName("dateOfBirth")
    @set:PropertyName("dateOfBirth")
    var dateOfBirth: String? = null,

    @get:PropertyName("gender")
    @set:PropertyName("gender")
    var gender: String? = null,

    @get:PropertyName("maritalStatus")
    @set:PropertyName("maritalStatus")
    var maritalStatus: String? = null,

    @get:PropertyName("externalDoctorId")
    @set:PropertyName("externalDoctorId")
    var externalDoctorId: String? = null,

    @get:PropertyName("constituentFile")
    @set:PropertyName("constituentFile")
    var constituentFile: ConstituentFile? = null,

    @get:PropertyName("prescriptions")
    @set:PropertyName("prescriptions")
    var prescriptions: MutableList<PatientPrescription> = mutableListOf(),

    @get:PropertyName("admitted")
    @set:PropertyName("admitted")
    var admitted: Boolean = false,

    @get:PropertyName("division")
    @set:PropertyName("division")
    var division: FacilityDivision? = null,

    @get:PropertyName("localDoctor")
    @set:PropertyName("localDoctor")
    var localDoctor: String? = null,

    @get:PropertyName("roomNumber")
    @set:PropertyName("roomNumber")
    var roomNumber: String? = null,

    @get:PropertyName("bedNumber")
    @set:PropertyName("bedNumber")
    var bedNumber: String? = null,

    @get:PropertyName("privateInsuranceNumber")
    @set:PropertyName("privateInsuranceNumber")
    var privateInsuranceNumber: String? = null
)