package seg3102.group25.wellmeadows.hmspms.domain.security.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.events.AccessDenied
import seg3102.group25.wellmeadows.hmspms.domain.security.events.AccessGranted
import seg3102.group25.wellmeadows.hmspms.domain.security.events.AccessRequested
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import java.util.*

class SecurityFacadeImpl(
    private val staffAccountRepository: StaffAccountRepository,
    private val eventEmitter: DomainEventEmitter
): SecurityFacade{
    override fun checkAccess(staffNumber: String, security: Security): Boolean {
        val domainEventRequested = AccessRequested(
            UUID.randomUUID(),
            Date(),
            staffNumber
        )
        eventEmitter.emit(domainEventRequested)
        security.logEvent(domainEventRequested)

        val existAccount = staffAccountRepository.findSync(staffNumber)

        if(staffNumber == "admin"){
            val domainEventGranted = AccessGranted(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
            eventEmitter.emit(domainEventGranted)
            security.logEvent(domainEventGranted)
            return true
        }
        else if (existAccount != null){
            if(security.checkAccess(existAccount.type!!)){
                val domainEventGranted = AccessGranted(
                    UUID.randomUUID(),
                    Date(),
                    staffNumber
                )
                eventEmitter.emit(domainEventGranted)
                security.logEvent(domainEventGranted)
                return true
            }
        }

        val domainEventDenied = AccessDenied(
            UUID.randomUUID(),
            Date(),
            staffNumber
        )
        eventEmitter.emit(domainEventDenied)
        security.logEvent(domainEventDenied)
        return false
    }

    override fun getLogs(): List<DomainEvent> {
        return Security.logger
    }

}