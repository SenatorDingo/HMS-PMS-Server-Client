package seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityStatus
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType

class FacilityDivision(
    var divisionId: String,
    var divisionName: String,
    var chargeNurseFirstName: String,
    var chargeNurseLastName: String,
    var chargeNurseTelExtension: String,
    var chargeNurseBipExtension: String,
    var location: String,
    var numberBeds: Int,
    var telephoneExtension: String
) {

    var facilityType: FacilityType = FacilityType.None
    var numberBedsAvailable: Int = numberBeds
    var status: FacilityStatus = FacilityStatus.Incomplete
    var shifts: MutableList<FacilityShift> = mutableListOf()
    var admissionWaitList: MutableList<FacilityAdmissionWaitList> = mutableListOf()
    var admissions: MutableList<Admission> = mutableListOf()

    fun update(updated: FacilityDivision){
        this.facilityType = updated.facilityType
        this.divisionId = updated.divisionId
        this.divisionName = updated.divisionName
        this.chargeNurseFirstName = updated.chargeNurseFirstName
        this.chargeNurseLastName = updated.chargeNurseLastName
        this.chargeNurseTelExtension = updated.chargeNurseTelExtension
        this.chargeNurseBipExtension = updated.chargeNurseBipExtension
        this.location = updated.location
        this.numberBeds = updated.numberBeds
        this.telephoneExtension = updated.telephoneExtension

        facilityType.setDivisionID(divisionId)
        facilityType.setDivisionName(divisionName)
    }

    fun setFacilityTypeCustom(facilityType: FacilityType){
        this.facilityType = facilityType
        this.facilityType.setDivisionID(divisionId)
        this.facilityType.setDivisionName(divisionName)
    }

    fun addAvailableBed(): Boolean{
        if(numberBedsAvailable >= numberBeds)
            return false

        numberBedsAvailable++
        return true
    }

    fun removeAvailableBed(): Boolean{
        if(numberBedsAvailable > 0 || status == FacilityStatus.Incomplete)
            numberBedsAvailable--
        else return false
        if(numberBedsAvailable<=0)
            status = FacilityStatus.Complete
        return true
    }

    fun isFull(): Boolean{
        return status == FacilityStatus.Complete
    }


    fun addShift(staffNumber: String, shiftType: ShiftType): Boolean{
        return shifts.add(FacilityShift(staffNumber, shiftType, this.divisionId))
    }

    fun removeShift(staffNumber: String, shiftType: ShiftType): Boolean{
        return shifts.removeIf { it.staffNumber == staffNumber && it.shiftType == shiftType }
    }

    fun addAdmissionWaitList(admissionWaitList: FacilityAdmissionWaitList): Boolean{
        return this.admissionWaitList.add(admissionWaitList)
    }

    fun removeAdmissionWaitList(patientNumber: String): Boolean{
        return admissionWaitList.removeIf { it.patientId == patientNumber }
    }


    fun addAdmission(admission: Admission): Boolean{
        if(admissions.any {
                it.patientNumber == admission.patientNumber ||
                        (it.bedNumber == admission.bedNumber && it.roomNumber == admission.roomNumber) })
            return false
        return admissions.add(admission)
    }

    fun removeAdmission(patientNumber: String): Boolean{
        admissions.removeIf { it.patientNumber == patientNumber }
        return true
    }

}