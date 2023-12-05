package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision;
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType;

class StaffShiftForm {
    var staffNumber: String? = null
    var shiftType: ShiftType? = null
    var division: String? = null
}