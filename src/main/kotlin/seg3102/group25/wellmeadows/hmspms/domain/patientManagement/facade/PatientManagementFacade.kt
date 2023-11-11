package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.*

interface PatientManagementFacade {
    fun staffLogIn(staffLogInInfo: StaffLogInDTO): Boolean
    fun staffLogOut(staffLogOutInfo: StaffLogOutDTO): Boolean
    fun checkLogged(staffNumber: String): Boolean

    /*
    fun requestAdmitPatient(staffNumber: String, admitPatientInfo: AdmitPatientDTO): Boolean
    fun requestAdmitPatientRequestList(staffNumber: String, admitPatientRequestListInfo: AdmitPatientRequestListDTO): Boolean
    fun requestDischargePatient(staffNumber: String, dischargePatientInfo: DischargePatientDTO): Boolean
    fun requestPatientAdmission(staffNumber: String, requestPatientAdmissionInfo: RequestPatientAdmissionDTO): Boolean
    fun requestPrescribeMedication(staffNumber: String, prescribeMedicationInfo: PrescribeMedicationDTO): Boolean
    fun requestRegisterPatient(staffNumber: String, registerPatientInfo: RegisterPatientDTO): Boolean
    fun requestRegisterStaff(staffNumber: String, registerStaffInfo: RegisterStaffDTO): Boolean
    fun requestUpdatePatientFile(staffNumber: String, updatePatientFileInfo: UpdatePatientFileDTO): Boolean
    fun requestVisualizeDivision(staffNumber: String, visualizeDivisionInfo: VisualizeDivisionDTO): FacilityDivision?
    fun requestConsultPatientFile(staffNumber: String, consultPatientFileInfo: ConsultPatientFileDTO): PatientFile?
    */
}