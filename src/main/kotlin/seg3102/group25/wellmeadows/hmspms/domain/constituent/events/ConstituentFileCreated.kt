package seg3102.group25.wellmeadows.hmspms.domain.constituent.events

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import java.util.*

class ConstituentFileCreated(
    val id: UUID,
    val occurredOn: Date,
    val staffNumber: String
): DomainEvent