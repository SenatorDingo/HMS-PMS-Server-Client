package seg3102.group25.wellmeadows.hmspms.domain.security.events

import java.util.*

class AccessRequested(
    val id: UUID,
    val occurredOn: Date,
    val staffNumber: String
)