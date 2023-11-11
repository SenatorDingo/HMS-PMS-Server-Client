package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.*
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.application.usecases.implementation.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.CheckLogged
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.StaffLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.StaffLoggedOut
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.isLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.logIn
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.logOut
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import java.util.*

class PatientManagementFacadeImpl(
    private val staffAccountRepository: StaffAccountRepository,
    private val eventEmitter: DomainEventEmitter
): PatientManagementFacade {
    override fun staffLogIn(staffLogInInfo: StaffLogInDTO): Boolean {
        val existingAccount = staffAccountRepository.find(staffLogInInfo.userId)
        if(existingAccount != null && logIn(existingAccount, staffLogInInfo.password)){
            eventEmitter.emit(
                    StaffLoggedIn(
                    UUID.randomUUID(),
                    Date(),
                    staffLogInInfo.userId
                )
            )
            return true
        }
        return false
    }

    override fun staffLogOut(staffLogOutInfo: StaffLogOutDTO): Boolean {
        if(logOut(staffLogOutInfo.userId)){
            eventEmitter.emit(
                StaffLoggedOut(
                    UUID.randomUUID(),
                    Date(),
                    staffLogOutInfo.userId
                )
            )
            return true
        }
        return false
    }

    override fun checkLogged(staffNumber: String): Boolean {
        eventEmitter.emit(
            CheckLogged(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return isLoggedIn(staffNumber)
    }

}