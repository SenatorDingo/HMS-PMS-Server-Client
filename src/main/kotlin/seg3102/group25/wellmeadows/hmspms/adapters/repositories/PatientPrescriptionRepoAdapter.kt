package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientPrescriptionRepository

class PatientPrescriptionRepoAdapter: PatientPrescriptionRepository {
    override fun find(prescriptionNumber: String): PatientPrescription? {
        TODO("Not yet implemented")
    }

    override fun save(patientPrescription: PatientPrescription): PatientPrescription {
        TODO("Not yet implemented")
    }
}