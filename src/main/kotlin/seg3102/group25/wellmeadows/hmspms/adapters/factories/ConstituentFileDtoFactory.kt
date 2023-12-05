package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.constituent.factories.ConstituentFileFactory

class ConstituentFileDtoFactory: ConstituentFileFactory {
    override fun createConstituentFile(registerPatientDTO: RegisterPatientDTO): ConstituentFile {
        return ConstituentFile(
            registerPatientDTO.firstName+registerPatientDTO.lastName,
            registerPatientDTO.nextOfKinFirstName,
            registerPatientDTO.nextOfKinLastName,
            registerPatientDTO.nextOfKinAddress,
            registerPatientDTO.nextOfKinTelephoneNumber,
            registerPatientDTO.nextOfKinRelationshipToPatient
        )
    }
}