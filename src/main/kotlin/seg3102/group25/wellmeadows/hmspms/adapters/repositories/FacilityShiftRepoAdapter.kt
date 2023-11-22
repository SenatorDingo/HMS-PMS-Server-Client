package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.ShiftRepository

class FacilityShiftRepoAdapter: ShiftRepository {
    override fun find(shift: FacilityShift): FacilityShift? {
        TODO("Not yet implemented")
    }

    override fun findAll(staffNumber: String): List<FacilityShift> {
        TODO("Not yet implemented")
    }

    override fun save(shift: FacilityShift): FacilityShift {
        TODO("Not yet implemented")
    }

    override fun remove(shift: FacilityShift): Boolean {
        TODO("Not yet implemented")
    }
}