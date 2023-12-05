package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.StaffShiftForm

class StaffShiftFormConverter {
    companion object {
        fun convertForm(staffShiftForm: StaffShiftForm): StaffShiftDTO {
            return StaffShiftDTO(
                    staffNumber = staffShiftForm.staffNumber!!,
                    shiftType = staffShiftForm.shiftType!!,
                    division = staffShiftForm.division!!
            )
        }
    }
}
