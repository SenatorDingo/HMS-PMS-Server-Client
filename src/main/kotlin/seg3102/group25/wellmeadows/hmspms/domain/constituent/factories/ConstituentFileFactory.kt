package seg3102.group25.wellmeadows.hmspms.domain.constituent.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile

interface ConstituentFileFactory {
    fun createConstituentFile(registerPatientDTO: RegisterPatientDTO): ConstituentFile
}