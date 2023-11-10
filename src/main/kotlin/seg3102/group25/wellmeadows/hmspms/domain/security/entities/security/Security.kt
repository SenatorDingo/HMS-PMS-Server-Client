package seg3102.group25.wellmeadows.hmspms.domain.security.entities.security

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType

class Security(
    private val accessLevel: AccessLevels
) {

    companion object{
        val logger: MutableList<DomainEvent> = ArrayList()
    }

    fun checkAccess(staffTypes: List<StaffType>): Boolean{
        var access = false
        for(staffType in staffTypes)
            access = access || accessLevel.staffType.contains(staffType)

        return access
    }

    fun logEvent(event: DomainEvent){
        logger.add(event)
    }

    // Won't be used by domain
    fun getEventLogs(): List<DomainEvent>{
        return logger
    }
}