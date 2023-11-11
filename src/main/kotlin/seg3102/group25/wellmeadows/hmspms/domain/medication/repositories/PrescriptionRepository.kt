package seg3102.group25.wellmeadows.hmspms.domain.medication.repositories

import seg3102.group25.wellmeadows.hmspms.domain.medication.entities.Prescription

interface PrescriptionRepository {

    fun store(prescription: Prescription): String
    fun retrieve(prescriptionID: String): Prescription?
}