package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.usecases.PrescribeMedication
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class PrescribeMedicationImpl: PrescribeMedication {

    val security: Security = Security(AccessLevels.PrescribeMedication)
    override fun prescribeMedication(
        patientId: String,
        drugNumber: String,
        drugName: String,
        unitsPerDay: Int,
        numAdminsPerDay: Int,
        adminTimes: List<String>,
        methodOfAdmin: String,
        startDate: String,
        finishDate: String
    ): String {
        TODO("Not yet implemented")
    }
}