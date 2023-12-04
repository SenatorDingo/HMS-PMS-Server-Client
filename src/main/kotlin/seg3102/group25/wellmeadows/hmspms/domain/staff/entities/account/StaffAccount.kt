package seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account

import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType

class StaffAccount(
    var employeeNumber: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var emailAddress: String,
) {
    var type: MutableList<StaffType> = mutableListOf()
    var facilityID: MutableList<String> =  mutableListOf() // Change to Facility Object
    var active: Boolean = true

    fun update(updated: StaffAccount){
        this.firstName = updated.firstName
        this.lastName = updated.lastName
        this.emailAddress = updated.emailAddress
        this.type = updated.type
        this.facilityID = updated.facilityID
        this.active = updated.active
    }

    fun updatePassword(password: String){
        this.password = password
    }

    fun updateTypes(types: List<StaffType>){
        this.type.clear()
        this.type.addAll(types)
    }

    fun addType(type: StaffType){
        this.type.add(type)
    }

    fun removeType(type: StaffType){
        this.type.remove(type)
    }

    fun updateFacilityIDs(facilityIDs: List<String>) {
        this.facilityID.clear()
        this.facilityID.addAll(facilityIDs)
    }

    fun addFacilityID(facilityID: String){
        this.facilityID.add(facilityID)
    }

    fun removeFacilityID(facilityID: String){
        this.facilityID.remove(facilityID)
    }

    fun checkPassword(password: String): Boolean{
        return password == this.password
    }

    fun deactivate() {
        this.active = false
    }
}