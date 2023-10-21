package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

import java.time.LocalDate

data class UpdatePatientFileDTO(
    var medicalStaffId: String,
    var patientId: String,
    var insuranceNumber: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var address: String? = null,
    var telephoneNumber: String? = null,
    var dateOfBirth: LocalDate? = null,
    var gender: String? = null,
    var maritalStatus: String? = null,
    var externalDoctorId: String? = null,
    var nextOfKinFullName: String? = null,
    var nextOfKinRelationshipToPatient: String? = null,
    var nextOfKinAddress: String? = null,
    var nextOfKinTelephoneNumber: String? = null
)