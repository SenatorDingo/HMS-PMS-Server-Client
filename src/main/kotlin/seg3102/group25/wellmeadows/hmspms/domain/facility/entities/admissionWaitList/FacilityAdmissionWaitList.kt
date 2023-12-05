package seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList

import java.util.*

class FacilityAdmissionWaitList(
        var patientId: String,
        var chargeNurseId: String,
        var division: String,
        var admissionStatus: String,
        var priority: Int,
        var createdOn: String
)