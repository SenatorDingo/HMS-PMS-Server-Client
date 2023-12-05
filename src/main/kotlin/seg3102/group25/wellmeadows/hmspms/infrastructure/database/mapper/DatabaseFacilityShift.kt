package seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper

import com.google.firebase.database.PropertyName

class DatabaseFacilityShift (
    @get:PropertyName("staffNumber")
    @set:PropertyName("staffNumber")
    var staffNumber: String? = null,

    @get:PropertyName("shiftType")
    @set:PropertyName("shiftType")
    var shiftType: String? = null,

    @get:PropertyName("division")
    @set:PropertyName("division")
    var division: String? = null
)