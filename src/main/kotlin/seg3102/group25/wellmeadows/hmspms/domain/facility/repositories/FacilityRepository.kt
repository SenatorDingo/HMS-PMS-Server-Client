package seg3102.group25.wellmeadows.hmspms.domain.facility.repositories

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType

interface FacilityRepository {
    suspend fun find(facilityIdentity: FacilityType): FacilityDivision?
    fun save(division: FacilityDivision): FacilityDivision
    fun findSync(facilityIdentity: FacilityType): FacilityDivision?
}