package seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType

data class FacilityShift(
    val staffNumber: String,
    val shiftType: ShiftType,
    val divisionID: String
)
