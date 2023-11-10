package seg3102.group25.wellmeadows.hmspms.domain.facility.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.CreateDivisionDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.Shift
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.Division
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType

interface FacilityFacade {
    fun createWardDivision(wardInfo: CreateDivisionDTO): Boolean
    fun createClinicDivision(clinicInfo: CreateDivisionDTO): Boolean
    fun updateDivision(divisionType: FacilityType, divisionInfo: CreateDivisionDTO): Boolean
    fun getDivision(divisionType: FacilityType): Division? // Same as VisualizeDivision(...)
    fun addAvailableBed(divisionType: FacilityType): Boolean
    fun removeAvailableBed(divisionType: FacilityType): Boolean
    fun isFull(divisionType: FacilityType): Boolean
    fun addShift(staffShiftInfo: StaffShiftDTO): Boolean
    fun removeShift(staffShiftInfo: StaffShiftDTO): Boolean
    fun getShifts(staffNumber: String): List<Shift> // return all shifts
}