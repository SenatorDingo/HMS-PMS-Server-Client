package seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account

import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType

class StaffAccount(
    val employeeNumber: String,
    private var password: String,
    private var firstName: String,
    private var lastName: String,
    private var emailAddress: String,
) {
    private var type: MutableList<StaffType> = ArrayList()
    private var facilityID: MutableList<String> = ArrayList() // Change to Facility Object
    private var active: Boolean = true

    fun update(updated: StaffAccount){
        this.firstName = updated.firstName
        this.lastName = updated.lastName
        this.emailAddress = updated.emailAddress
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

    fun getTypes(): List<StaffType> {
        return this.type.toList()
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
        this.active = false
    }

    fun checkPassword(password: String): Boolean{
        return this.password == password
    }

    fun getPassword(): String {
        return this.password
    }

    fun getFirstName(): String {
        return this.firstName
    }

    fun getLastName(): String {
        return this.lastName
    }

    fun getEmailAddress(): String {
        return this.emailAddress
    }

    fun getType(): MutableList<StaffType> {
        return type
    }

    fun isActive(): Boolean {
        return active
    }
}