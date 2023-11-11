package seg3102.group25.wellmeadows.hmspms.domain.facility.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift

interface ShiftFactory {
    fun createShift(createShiftDTO: StaffShiftDTO): FacilityShift
}