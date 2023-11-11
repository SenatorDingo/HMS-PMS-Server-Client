package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import java.util.*

class RequestPatientAdmission(
    val id: UUID,
    val occurredOn: Date,
    val staffNumber: String
): DomainEvent