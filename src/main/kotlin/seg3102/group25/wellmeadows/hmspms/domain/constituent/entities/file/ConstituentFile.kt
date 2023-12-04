package seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file

import seg3102.group25.wellmeadows.hmspms.domain.constituent.valueObjects.ConstituentType

class ConstituentFile(
    var constituentID: String,
    var firstName: String,
    var lastName: String,
    var address: String,
    var telephoneNumber: String,
    var relationship: String
) {

    /*
     *   For this implementation there is no other form on constituent.
     *   However, in time, if other forms of constituent are needed (e.g. Visitor), this can be reused.
    */
    var constituentType: ConstituentType = ConstituentType.NextOfKin

    fun update(updated: ConstituentFile){
        this.constituentID = updated.constituentID
        this.firstName = updated.firstName
        this.lastName = updated.lastName
        this.address = updated.address
        this.telephoneNumber = updated.telephoneNumber
        this.relationship = updated.relationship
    }

}