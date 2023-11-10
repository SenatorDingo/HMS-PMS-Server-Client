package seg3102.group25.wellmeadows.hmspms.domain.facility.entities

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.Division
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType

data class Shift(
    val staffNumber: String,
    val shiftType: ShiftType,
    val division: Division
)
