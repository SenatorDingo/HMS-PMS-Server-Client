package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.repositories

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType

class FacilityRepositoryStub : FacilityRepository {
    override suspend fun find(facilityIdentity: FacilityType): FacilityDivision? {
        TODO("Not yet implemented")
    }

    override fun save(division: FacilityDivision): FacilityDivision {
        TODO("Not yet implemented")
    }

    override fun findSync(facilityIdentity: FacilityType): FacilityDivision? {
        TODO("Not yet implemented")
    }
}