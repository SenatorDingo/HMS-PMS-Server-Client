package seg3102.group25.wellmeadows.hmspms.domain.patient.repositories

import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription

interface PatientPrescriptionRepository {
    fun find(prescriptionNumber: String): PatientPrescription?
    fun save(patientPrescription: PatientPrescription): PatientPrescription
}