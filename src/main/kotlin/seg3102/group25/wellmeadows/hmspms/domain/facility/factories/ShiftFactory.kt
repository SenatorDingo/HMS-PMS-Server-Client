package seg3102.group25.wellmeadows.hmspms.domain.facility.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.Shift

interface ShiftFactory {
    fun createShift(createShiftDTO: StaffShiftDTO): Shift
}