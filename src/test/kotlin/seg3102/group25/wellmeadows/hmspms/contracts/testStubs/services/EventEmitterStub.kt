package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.services

import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent

class EventEmitterStub : DomainEventEmitter {
    override fun emit(event: DomainEvent) {
        TODO("Not yet implemented")
    }
}