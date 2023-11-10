package seg3102.group25.wellmeadows.hmspms.domain.facility.events

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import java.util.*

class DivisionCreated(
    val id: UUID,
    val occurredOn: Date,
    val divisionType: String,
    val divisionNumber: String
): DomainEvent