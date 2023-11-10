package seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.Shift
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityStatus
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType

class Division(
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

    private var facilityType: FacilityType = FacilityType.None
    private var numberBedsAvailable: Int = numberBeds
    private var status: FacilityStatus = FacilityStatus.Incomplete
    private val shifts: MutableList<Shift> = ArrayList()


    fun update(updated: Division){
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

    fun setFacilityType(facilityType: FacilityType){
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

    fun getShifts(): List<Shift>{
        return shifts
    }

    fun addShift(staffNumber: String, shiftType: ShiftType): Boolean{
        return shifts.add(Shift(staffNumber, shiftType, this))
    }

    fun removeShift(staffNumber: String, shiftType: ShiftType): Boolean{
        return shifts.removeIf { it.staffNumber == staffNumber && it.shiftType == shiftType }
    }
}