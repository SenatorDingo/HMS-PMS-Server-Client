package seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade

interface PatientManagementFacade {
    fun staffLogIn(staffNumber:String, password: String): Boolean
    fun staffLogOut(staffNumber: String): Boolean
    fun requestAdmitPatient(staffNumber: String): Boolean
    fun requestAdmitPatientRequestList(staffNumber: String): Boolean
    fun requestDischargePatient(staffNumber: String): Boolean
    fun requestPatientAdmission(staffNumber: String): Boolean
    fun requestPrescribeMedication(staffNumber: String): Boolean
    fun requestRegisterPatient(staffNumber: String): Boolean
    fun requestRegisterStaff(staffNumber: String): Boolean
    fun requestUpdatePatientFile(staffNumber: String): Boolean
}