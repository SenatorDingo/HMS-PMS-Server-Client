package seg3102.group25.wellmeadows.hmspms.domain.facility.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
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
    fun getAdmissionWaitList(divisionType: FacilityType): List<FacilityAdmissionWaitList>
    fun createAdmissionWaitList(admissionWaitListInfo: RequestPatientAdmissionDTO): Boolean
    fun removeAdmissionWaitList(divisionType: FacilityType, patientID: String): Boolean
    fun getAdmissions(divisionType: FacilityType): List<Admission>
    fun addAdmission(divisionType: FacilityType, admissionInfo: AdmitPatientDTO): Boolean
    fun removeAdmission(divisionType: FacilityType, patientID: String): Boolean
}