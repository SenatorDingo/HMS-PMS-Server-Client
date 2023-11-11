package seg3102.group25.wellmeadows.hmspms.domain.facility.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.CreateDivisionDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType

interface FacilityFacade {
    fun createWardDivision(wardInfo: CreateDivisionDTO): Boolean
    fun createClinicDivision(clinicInfo: CreateDivisionDTO): Boolean
    fun updateDivision(divisionType: FacilityType, divisionInfo: CreateDivisionDTO): Boolean
    fun getDivision(divisionType: FacilityType): FacilityDivision? // Same as VisualizeDivision(...)
    fun addAvailableBed(divisionType: FacilityType): Boolean
    fun removeAvailableBed(divisionType: FacilityType): Boolean
    fun isFull(divisionType: FacilityType): Boolean
    fun addShift(staffShiftInfo: StaffShiftDTO): Boolean
    fun removeShift(staffShiftInfo: StaffShiftDTO): Boolean
    fun getShifts(staffNumber: String): List<FacilityShift> // return all shifts
}