package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.CreateDivisionDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityFactory

class FacilityFactoryStub : FacilityFactory {
    override fun createDivision(createDivisionDTO: CreateDivisionDTO): FacilityDivision {
        TODO("Not yet implemented")
    }
}