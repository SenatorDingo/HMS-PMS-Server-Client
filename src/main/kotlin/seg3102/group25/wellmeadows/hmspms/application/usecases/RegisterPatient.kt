package seg3102.group25.wellmeadows.hmspms.application.usecases

// Define an interface for registering patients
interface RegisterPatient {
    fun registerPatient(
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
    ): String
}