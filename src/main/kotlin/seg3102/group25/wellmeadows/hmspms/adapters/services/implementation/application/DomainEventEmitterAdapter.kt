package seg3102.group25.wellmeadows.hmspms.adapters.services.implementation.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent

@Component
class DomainEventEmitterAdapter: DomainEventEmitter {

    override fun emit(event: DomainEvent) {
        println(event.toString())
    }
}