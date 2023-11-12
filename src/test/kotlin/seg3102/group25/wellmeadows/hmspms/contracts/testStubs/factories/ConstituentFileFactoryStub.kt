package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.constituent.factories.ConstituentFileFactory

class ConstituentFileFactoryStub : ConstituentFileFactory {
    override fun createConstituentFile(registerPatientDTO: RegisterPatientDTO): ConstituentFile {
        TODO("Not yet implemented")
    }
}