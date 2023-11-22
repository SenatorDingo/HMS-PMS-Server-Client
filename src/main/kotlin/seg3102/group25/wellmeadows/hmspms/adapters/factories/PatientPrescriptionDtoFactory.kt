package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientPrescriptionFactory

class PatientPrescriptionDtoFactory: PatientPrescriptionFactory {
    override fun createPatientPrescription(prescribeMedicationDTO: PrescribeMedicationDTO): PatientPrescription {
        TODO("Not yet implemented")
    }
}