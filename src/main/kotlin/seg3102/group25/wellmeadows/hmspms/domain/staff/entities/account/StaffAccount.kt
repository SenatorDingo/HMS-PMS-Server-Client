package seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account

import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.Role

class StaffAccount(
    val employeeNumber: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var emailAddress: String,
) {
    var role: MutableList<Role> = ArrayList()
    var facilityID: MutableList<String> = ArrayList() // Change to Facility Object
    var active: Boolean = true

    fun update(updated: StaffAccount){
        this.firstName = updated.firstName
        this.lastName = updated.lastName
        this.emailAddress = updated.emailAddress
    }

    fun updatePassword(password: String){
        this.password = password
    }

    fun updateRoles(roles: List<Role>){
        this.role.clear()
        this.role.addAll(roles)
    }

    fun addRole(role: Role){
        this.role.add(role)
    }

    fun removeRole(role: Role){
        this.role.remove(role)
    }

    fun getRoles(): List<Role> {
        return this.role.toList()
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

    fun getFacilityIDs(): List<String> { // Change to Facility Object
        return this.facilityID.toList()
    }

    fun deactivate(){
        this.active = false;
    }

}