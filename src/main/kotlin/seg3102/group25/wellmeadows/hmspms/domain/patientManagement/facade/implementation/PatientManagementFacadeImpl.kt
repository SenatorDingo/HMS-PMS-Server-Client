package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.*
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.application.usecases.implementation.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
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

    override fun requestAdmitPatient(staffNumber: String, admitPatientInfo: AdmitPatientDTO): Boolean {
        if(checkAuthorized(staffNumber, AdmitPatientImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestAdmitPatientRequestList(
        staffNumber: String,
        admitPatientRequestListInfo: AdmitPatientRequestListDTO
    ): Boolean {
        if(checkAuthorized(staffNumber, AdmitPatientRequestListImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestDischargePatient(staffNumber: String, dischargePatientInfo: DischargePatientDTO): Boolean {
        if(checkAuthorized(staffNumber, DischargePatientImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestPatientAdmission(
        staffNumber: String,
        requestPatientAdmissionInfo: RequestPatientAdmissionDTO
    ): Boolean {
        if(checkAuthorized(staffNumber, RequestPatientAdmissionImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestPrescribeMedication(
        staffNumber: String,
        prescribeMedicationInfo: PrescribeMedicationDTO
    ): Boolean {
        if(checkAuthorized(staffNumber, PrescribeMedicationImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestRegisterPatient(staffNumber: String, registerPatientInfo: RegisterPatientDTO): Boolean {
        if(checkAuthorized(staffNumber, RegisterPatientImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestRegisterStaff(staffNumber: String, registerStaffInfo: RegisterStaffDTO): Boolean {
        if(checkAuthorized(staffNumber, RegisterStaffImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestUpdatePatientFile(staffNumber: String, updatePatientFileInfo: UpdatePatientFileDTO): Boolean {
        if(checkAuthorized(staffNumber, UpdatePatientFileImpl().security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return false
    }

    override fun requestVisualizeDivision(
        staffNumber: String,
        visualizeDivisionInfo: VisualizeDivisionDTO
    ): FacilityDivision? {
        val security: Security = Security(AccessLevels.VisualizeDivision)
        val facilityDivision: FacilityDivision? = null
        if(checkAuthorized(staffNumber, security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return facilityDivision
    }

    override fun requestConsultPatientFile(
        staffNumber: String,
        consultPatientFileInfo: ConsultPatientFileDTO
    ): PatientFile? {
        val security: Security = Security(AccessLevels.ConsultPatientFile)
        val patientFile: PatientFile? = null
        if(checkAuthorized(staffNumber, security) && isLoggedIn(staffNumber)) {
            TODO("Not yet implemented")
        }
        return patientFile
    }

    private fun checkAuthorized(staffNumber: String, security: Security): Boolean{
        val staff = staffAccountRepository.find(staffNumber)
        if (staff != null) {
            return security.checkAccess(staff.getTypes())
        }
        return false
    }
}