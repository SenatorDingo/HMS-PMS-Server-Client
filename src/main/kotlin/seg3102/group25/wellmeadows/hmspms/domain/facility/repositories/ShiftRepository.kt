package seg3102.group25.wellmeadows.hmspms.domain.facility.repositories

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift

interface ShiftRepository {
    suspend fun find(shift: FacilityShift): FacilityShift?
    fun findAll(staffNumber: String): List<FacilityShift>
    fun save(shift: FacilityShift): FacilityShift
    fun remove(shift: FacilityShift): Boolean
    fun findSync(shift: FacilityShift): FacilityShift?
}