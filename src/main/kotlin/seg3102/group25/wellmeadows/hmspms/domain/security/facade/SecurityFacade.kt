package seg3102.group25.wellmeadows.hmspms.domain.security.facade

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security

interface SecurityFacade {
    fun checkAccess(staffNumber: String, security: Security): Boolean
    fun getLogs(): List<DomainEvent>
}