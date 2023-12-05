package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.ShiftFactory

class ShiftDtoFactory: ShiftFactory {
    override fun createShift(createShiftDTO: StaffShiftDTO): FacilityShift {
        return FacilityShift(
            createShiftDTO.staffNumber,
            createShiftDTO.shiftType,
            createShiftDTO.division
        )
    }
}