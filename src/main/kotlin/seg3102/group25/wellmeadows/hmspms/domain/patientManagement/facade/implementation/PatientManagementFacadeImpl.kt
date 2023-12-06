package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.*
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.application.usecases.*
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.events.*
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

        val existingAccount = staffAccountRepository.findSync(staffLogInInfo.userId)
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

    override fun requestAdmitPatient(
        staffNumber: String, admitPatientInfo: AdmitPatientDTO
    ): Boolean {
        eventEmitter.emit(
            RequestAdmitPatient(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return admitPatientUseCase.admitPatient(staffNumber, admitPatientInfo)
    }

    override fun requestAdmitPatientRequestList(
        staffNumber: String,
        admitPatientInfo: AdmitPatientDTO,
        admitPatientRequestListInfo: AdmitPatientRequestListDTO
    ): Boolean {
        eventEmitter.emit(
            RequestAdmitPatientRequestList(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return admitPatientRequestListUseCase.admitPatientFromRequestList(staffNumber, admitPatientRequestListInfo, admitPatientInfo)
    }

    override fun requestDischargePatient(staffNumber: String, dischargePatientInfo: DischargePatientDTO, divisionID: String): Boolean {
        eventEmitter.emit(
            RequestDischargePatient(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return dischargePatientUseCase.dischargePatient(staffNumber, dischargePatientInfo, divisionID)
    }

    override fun requestPatientAdmission(
        staffNumber: String,
        requestPatientAdmissionInfo: RequestPatientAdmissionDTO
    ): Boolean {
        eventEmitter.emit(
            RequestRequestPatientAdmission(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return requestPatientAdmissionUseCase.requestAdmission(staffNumber, requestPatientAdmissionInfo)
    }

    override fun requestPrescribeMedication(
        staffNumber: String,
        prescribeMedicationInfo: PrescribeMedicationDTO
    ): Boolean {
        eventEmitter.emit(
            RequestPrescribeMedication(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return prescribeMedicationUseCase.prescribeMedication(staffNumber, prescribeMedicationInfo)
    }

    override fun requestRegisterPatient(staffNumber: String, registerPatientInfo: RegisterPatientDTO): Boolean {
        eventEmitter.emit(
            RequestRegisterPatient(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return registerPatientUseCase.registerPatient(staffNumber, registerPatientInfo)
    }

    override fun requestRegisterStaff(staffNumber: String, registerStaffInfo: RegisterStaffDTO): Boolean {
        eventEmitter.emit(
            RequestRegisterStaff(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return registerStaffUseCase.registerStaff(staffNumber, registerStaffInfo)
    }

    override fun requestUpdatePatientFile(staffNumber: String, updatePatientFileInfo: UpdatePatientFileDTO): Boolean {
        eventEmitter.emit(
            RequestUpdatePatientFile(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return updatePatientFileUseCase.updatePatientFile(staffNumber, updatePatientFileInfo)
    }

    override fun requestUpdateStaffShift(staffNumber: String, updateStaffShift: StaffShiftDTO): Boolean {
        eventEmitter.emit(
            RequestUpdatePatientFile(
                UUID.randomUUID(),
                Date(),
                staffNumber
            )
        )
        return registerStaffUseCase.updateShift(staffNumber, updateStaffShift)
    }

}
