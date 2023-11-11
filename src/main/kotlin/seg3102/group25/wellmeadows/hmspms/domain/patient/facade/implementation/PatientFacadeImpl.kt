package seg3102.group25.wellmeadows.hmspms.domain.patient.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade

class PatientFacadeImpl: PatientFacade {
    override fun createPatientFile(patientDTO: RegisterPatientDTO): Boolean {
        TODO("Not yet implemented")
    }

    override fun updatePatientFile(patientDTO: RegisterPatientDTO): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPatientFile(patientNumber: String): PatientFile? {
        TODO("Not yet implemented")
    }

    override fun createPatientPrescription(prescriptionDTO: PrescribeMedicationDTO): Boolean {
        TODO("Not yet implemented")
    }

    override fun updatePatientPrescription(prescriptionDTO: PrescribeMedicationDTO): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPatientPrescriptions(patientNumber: String): List<PatientPrescription> {
        TODO("Not yet implemented")
    }

    override fun admitPatient(patientNumber: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun dischargePatient(patientNumber: String): Boolean {
        TODO("Not yet implemented")
    }
}