package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

import java.time.LocalDate

data class RegisterPatientDTO(
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
    var nextOfKinFirstName: String,
    var nextOfKinLastName: String,
    var nextOfKinRelationshipToPatient: String,
    var nextOfKinAddress: String,
    var nextOfKinTelephoneNumber: String
)
