package seg3102.group25.wellmeadows.hmspms.domain.constituent.facade

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile

interface ConstituentFacade {

    fun createConstituentNOKFile(accountInfo: RegisterPatientDTO): Boolean
    fun updateConstituentNOKFile(constituentID: String, accountInfo: RegisterPatientDTO): Boolean
    fun getConstituentNOKFile(constituentID: String): ConstituentFile?
}