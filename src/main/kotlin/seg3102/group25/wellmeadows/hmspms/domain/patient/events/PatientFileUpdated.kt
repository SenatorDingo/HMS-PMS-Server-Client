package seg3102.group25.wellmeadows.hmspms.domain.patient.events

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import java.util.*

class PatientFileUpdated(
    val id: UUID,
    val occurredOn: Date,
    val patientNumber: String
): DomainEvent