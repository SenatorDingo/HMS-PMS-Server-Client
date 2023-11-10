package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

data class CreateDivisionDTO(
    var divisionId: String,
    var divisionName: String,
    var chargeNurseFirstName: String,
    var chargeNurseLastName: String,
    var chargeNurseTelExtension: String,
    var chargeNurseBipExtension: String,
    var location: String,
    var numberBeds: Int,
    var telephoneExtension: String
)
