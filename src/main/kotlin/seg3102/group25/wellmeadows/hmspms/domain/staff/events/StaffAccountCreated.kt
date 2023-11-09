package seg3102.group25.wellmeadows.hmspms.domain.staff.events

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import java.util.*

class StaffAccountCreated(
    val id: UUID,
    val occurredOn: Date,
    val staffNumber: String
): DomainEvent