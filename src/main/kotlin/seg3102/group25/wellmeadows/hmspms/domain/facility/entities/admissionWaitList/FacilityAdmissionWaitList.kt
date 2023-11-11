package seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList

import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import java.util.*

class FacilityAdmissionWaitList(
    var patientId: String,
    var chargeNurseId: String,
    var division: FacilityType,
    var admissionStatus: String,
    var createdOn: Date
)