package seg3102.group25.wellmeadows.hmspms.application.services

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent

interface DomainEventEmitter {
    fun emit(event: DomainEvent)
}