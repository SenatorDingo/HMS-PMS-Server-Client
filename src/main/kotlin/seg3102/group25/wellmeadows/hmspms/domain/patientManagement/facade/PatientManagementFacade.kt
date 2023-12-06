package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade

import org.springframework.stereotype.Component
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.*

@Component
interface PatientManagementFacade {
    fun staffLogIn(staffLogInInfo: StaffLogInDTO): Boolean
    fun staffLogOut(staffLogOutInfo: StaffLogOutDTO): Boolean
    fun checkLogged(staffNumber: String): Boolean

    fun requestAdmitPatient(staffNumber: String, admitPatientInfo: AdmitPatientDTO): Boolean
    fun requestAdmitPatientRequestList(staffNumber: String, admitPatientInfo: AdmitPatientDTO, admitPatientRequestListInfo: AdmitPatientRequestListDTO): Boolean
    fun requestDischargePatient(staffNumber: String, dischargePatientInfo: DischargePatientDTO, divisionID:String): Boolean
    fun requestPatientAdmission(staffNumber: String, requestPatientAdmissionInfo: RequestPatientAdmissionDTO): Boolean
    fun requestPrescribeMedication(staffNumber: String, prescribeMedicationInfo: PrescribeMedicationDTO): Boolean
    fun requestRegisterPatient(staffNumber: String, registerPatientInfo: RegisterPatientDTO): Boolean
    fun requestRegisterStaff(staffNumber: String, registerStaffInfo: RegisterStaffDTO): Boolean
    fun requestUpdatePatientFile(staffNumber: String, updatePatientFileInfo: UpdatePatientFileDTO): Boolean
    fun requestUpdateStaffShift(staffNumber: String, updateStaffShift: StaffShiftDTO): Boolean
}
