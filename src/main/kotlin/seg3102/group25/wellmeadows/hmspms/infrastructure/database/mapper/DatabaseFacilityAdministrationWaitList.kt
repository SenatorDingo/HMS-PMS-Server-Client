package seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper

import com.google.firebase.database.PropertyName

class DatabaseFacilityAdministrationWaitList (
    @get:PropertyName("patientId")
    @set:PropertyName("patientId")
    var patientId: String? = null,

    @get:PropertyName("chargeNurseId")
    @set:PropertyName("chargeNurseId")
    var chargeNurseId: String? = null,

    @get:PropertyName("division")
    @set:PropertyName("division")
    var division: String? = null,

    @get:PropertyName("admissionStatus")
    @set:PropertyName("admissionStatus")
    var admissionStatus: String? = null,

    @get:PropertyName("priority")
    @set:PropertyName("priority")
    var priority: Int? = -1,

    @get:PropertyName("createdOn")
    @set:PropertyName("createdOn")
    var createdOn: String? = null
)