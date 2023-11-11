package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO

interface PrescribeMedication {
    fun prescribeMedication(staffNumber: String, prescribeMedicationInfo: PrescribeMedicationDTO): Boolean
}