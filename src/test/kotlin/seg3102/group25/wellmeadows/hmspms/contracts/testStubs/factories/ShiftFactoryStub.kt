package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.ShiftFactory

class ShiftFactoryStub : ShiftFactory {
    override fun createShift(createShiftDTO: StaffShiftDTO): FacilityShift {
        TODO("Not yet implemented")
    }
}