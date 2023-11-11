package seg3102.group25.wellmeadows.hmspms.domain.medication.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO

interface PrescriptionFactory {
       fun createPrescription(prescribeMedicationDTO: PrescribeMedicationDTO)
}