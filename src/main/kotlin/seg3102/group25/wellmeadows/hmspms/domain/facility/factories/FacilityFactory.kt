package seg3102.group25.wellmeadows.hmspms.domain.facility.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.CreateDivisionDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision

interface FacilityFactory {
    fun createDivision(createDivisionDTO: CreateDivisionDTO): FacilityDivision
}