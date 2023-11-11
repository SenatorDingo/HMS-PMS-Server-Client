package seg3102.group25.wellmeadows.hmspms.domain.patient.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription

interface PatientPrescriptionFactory {
    fun createPatientPrescription(prescribeMedicationDTO: PrescribeMedicationDTO): PatientPrescription
}