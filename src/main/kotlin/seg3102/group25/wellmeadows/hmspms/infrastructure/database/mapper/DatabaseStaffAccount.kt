package seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper

import com.google.cloud.firestore.annotation.PropertyName
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType

class DatabaseStaffAccount(
    @get:PropertyName("employeeNumber")
    @set:PropertyName("employeeNumber")
    var employeeNumber: String? = null,

    @get:PropertyName("password")
    @set:PropertyName("password")
    var password: String? = null,

    @get:PropertyName("firstName")
    @set:PropertyName("firstName")
    var firstName: String? = null,

    @get:PropertyName("lastName")
    @set:PropertyName("lastName")
    var lastName: String? = null,

    @get:PropertyName("emailAddress")
    @set:PropertyName("emailAddress")
    var emailAddress: String? = null,

    @get:PropertyName("type")
    @set:PropertyName("type")
    var type: MutableList<StaffType>? = mutableListOf(),

    @get:PropertyName("facilityID")
    @set:PropertyName("facilityID")
    var facilityID: MutableList<String>? = mutableListOf(),

    @get:PropertyName("active")
    @set:PropertyName("active")
    var active: Boolean? = true
)