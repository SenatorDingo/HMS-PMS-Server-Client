package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

data class RegisterStaffDTO(
    var employeeNumber: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var emailAddress: String
)