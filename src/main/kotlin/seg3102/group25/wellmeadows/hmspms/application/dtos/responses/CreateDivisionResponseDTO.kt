package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class CreateDivisionResponseDTO(
    var success: Boolean,
    var message: String,
    var divisionId: String?
)
