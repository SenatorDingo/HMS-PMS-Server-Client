package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.*
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.application.usecases.*
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.CheckLogged
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.StaffLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.StaffLoggedOut
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.isLoggedIn
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.logIn
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security.Companion.logOut
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import java.util.*

class PatientManagementFacadeImpl(
    private val staffAccountRepository: StaffAccountRepository,
    private val eventEmitter: DomainEventEmitter,

    private val admitPatientUseCase: AdmitPatient,
    private val admitPatientRequestListUseCase: AdmitPatientRequestList,
    private val dischargePatientUseCase: DischargePatient,
    private val prescribeMedicationUseCase: PrescribeMedication,
    private val registerPatientUseCase: RegisterPatient,
    private val registerStaffUseCase: RegisterStaff,
    private val requestPatientAdmissionUseCase: RequestPatientAdmission,
    private val updatePatientFileUseCase: UpdatePatientFile
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

    override fun requestAdmitPatient(staffNumber: String, admitPatientInfo: AdmitPatientDTO): Boolean {
        return admitPatientUseCase.admitPatient(staffNumber, admitPatientInfo)
    }

    override fun requestAdmitPatientRequestList(
        staffNumber: String,
        admitPatientRequestListInfo: AdmitPatientRequestListDTO
    ): Boolean {
        return admitPatientRequestListUseCase.admitPatientFromRequestList(staffNumber, admitPatientRequestListInfo)
    }

    override fun requestDischargePatient(staffNumber: String, dischargePatientInfo: DischargePatientDTO): Boolean {
        return dischargePatientUseCase.dischargePatient(staffNumber, dischargePatientInfo)
    }

    override fun requestPatientAdmission(
        staffNumber: String,
        requestPatientAdmissionInfo: RequestPatientAdmissionDTO
    ): Boolean {
        return requestPatientAdmissionUseCase.requestAdmission(staffNumber, requestPatientAdmissionInfo)
    }

    override fun requestPrescribeMedication(
        staffNumber: String,
        prescribeMedicationInfo: PrescribeMedicationDTO
    ): Boolean {
        return prescribeMedicationUseCase.prescribeMedication(staffNumber, prescribeMedicationInfo)
    }

    override fun requestRegisterPatient(staffNumber: String, registerPatientInfo: RegisterPatientDTO): Boolean {
        return registerPatientUseCase.registerPatient(staffNumber, registerPatientInfo)
    }

    override fun requestRegisterStaff(staffNumber: String, registerStaffInfo: RegisterStaffDTO): Boolean {
        return registerStaffUseCase.registerStaff(staffNumber, registerStaffInfo)
    }

    override fun requestUpdatePatientFile(staffNumber: String, updatePatientFileInfo: UpdatePatientFileDTO): Boolean {
        return updatePatientFileUseCase.updatePatientFile(staffNumber, updatePatientFileInfo)
    }

}