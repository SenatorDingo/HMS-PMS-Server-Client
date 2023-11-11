package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.application.usecases.implementation.*
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.StaffLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.StaffLoggedOut
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.isLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.logIn
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.logOut
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import java.util.*

class PatientManagementFacadeImpl(
    private val staffAccountRepository: StaffAccountRepository,
    private val eventEmitter: DomainEventEmitter
): PatientManagementFacade {
    override fun staffLogIn(staffNumber: String, password: String): Boolean {
        if(logIn(staffNumber)){
            eventEmitter.emit(
                    StaffLoggedIn(
                    UUID.randomUUID(),
                    Date(),
                    staffNumber
                )
            )
            return true
        }
        return false
    }

    override fun staffLogOut(staffNumber: String): Boolean {
        if(logOut(staffNumber)){
            eventEmitter.emit(
                StaffLoggedOut(
                    UUID.randomUUID(),
                    Date(),
                    staffNumber
                )
            )
            return true
        }
        return false
    }

    override fun requestAdmitPatient(staffNumber: String): Boolean {
        if(checkAuthorized(staffNumber, AdmitPatientImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestAdmitPatientRequestList(staffNumber: String): Boolean {
        if(checkAuthorized(staffNumber, AdmitPatientRequestListImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestDischargePatient(staffNumber: String): Boolean {
        if(checkAuthorized(staffNumber, DischargePatientImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestPatientAdmission(staffNumber: String): Boolean {
        if(checkAuthorized(staffNumber, RequestPatientAdmissionImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestPrescribeMedication(staffNumber: String): Boolean {
        if(checkAuthorized(staffNumber, PrescribeMedicationImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestRegisterPatient(staffNumber: String): Boolean {
        if(checkAuthorized(staffNumber, RegisterPatientImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestRegisterStaff(staffNumber: String): Boolean {
        if(checkAuthorized(staffNumber, RegisterStaffImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestUpdatePatientFile(staffNumber: String): Boolean {
        if(checkAuthorized(staffNumber, UpdatePatientFileImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    private fun checkAuthorized(staffNumber: String, security: Security): Boolean{
        val staff = staffAccountRepository.find(staffNumber)
        if (staff != null) {
            return security.checkAccess(staff.getTypes())
        }
        return false
    }
}