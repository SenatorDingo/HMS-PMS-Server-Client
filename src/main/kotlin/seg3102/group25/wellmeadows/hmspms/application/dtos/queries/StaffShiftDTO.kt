package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType

data class StaffShiftDTO(
    val staffNumber: String,
    val shiftType: ShiftType,
    val division: String
)