package seg3102.group25.wellmeadows.hmspms.domain.facility.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.CreateDivisionDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType

interface FacilityFacade {
    fun createWardDivision(wardInfo: CreateDivisionDTO): Boolean
    fun createClinicDivision(clinicInfo: CreateDivisionDTO): Boolean
    fun updateDivision(divisionType: FacilityType, divisionInfo: CreateDivisionDTO): Boolean
    fun addAvailableBed(divisionType: FacilityType): Boolean
    fun removeAvailableBed(divisionType: FacilityType): Boolean
    fun isFull(divisionType: FacilityType): Boolean
}