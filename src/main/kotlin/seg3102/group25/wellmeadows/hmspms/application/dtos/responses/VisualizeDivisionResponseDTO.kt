package seg3102.group25.wellmeadows.hmspms.application.dtos.responses

data class VisualizeDivisionResponseDTO(
    var success: Boolean,
    var message: String,
    var divisionDetails: DivisionDetailsDTO?
)

data class DivisionDetailsDTO(
    var divisionId: String,
    var divisionName: String,
)
