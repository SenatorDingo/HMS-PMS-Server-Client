package seg3102.group25.wellmeadows.hmspms.domain.patient.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.UpdatePatientFileDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription

interface PatientFacade {
    fun createPatientFile(patientDTO: RegisterPatientDTO): Boolean
    fun updatePatientFile(updatePatientDTO: UpdatePatientFileDTO): Boolean
    fun getPatientFile(patientNumber: String): PatientFile?
    fun createPatientPrescription(prescriptionDTO: PrescribeMedicationDTO): Boolean
    fun updatePatientPrescription(prescriptionDTO: PrescribeMedicationDTO): Boolean
    fun getPatientPrescriptions(patientNumber: String): List<PatientPrescription>
    fun admitPatient(admitPatientInfo: AdmitPatientDTO, patientNumber: String, division: FacilityDivision): Boolean
    fun dischargePatient(patientNumber: String, divisionID: String): Boolean
}