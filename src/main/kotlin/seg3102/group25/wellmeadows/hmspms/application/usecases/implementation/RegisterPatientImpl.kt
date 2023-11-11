package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.usecases.RegisterPatient
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels

class RegisterPatientImpl: RegisterPatient {

    val security: Security = Security(AccessLevels.RegisterPatient)
    override fun registerPatient(
        uniqueInsuranceNumber: String,
        firstName: String,
        lastName: String,
        address: String,
        telephoneNumber: String,
        dateOfBirth: String,
        gender: String,
        maritalStatus: String,
        externalDoctor: String,
        nextOfKinID: String
    ): Boolean {
        TODO("Not yet implemented")
    }
}