package seg3102.group25.wellmeadows.hmspms.domain.facility.repositories

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.Shift

interface ShiftRepository {
    fun find(shift: Shift): Shift?
    fun findAll(staffNumber: String): List<Shift>
    fun save(shift: Shift): Shift
}