package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.repositories

import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.constituent.repositories.ConstituentFileRepository

class ConstituentFileRepositoryStub : ConstituentFileRepository {
    override suspend fun find(constituentID: String): ConstituentFile? {
        TODO("Not yet implemented")
    }

    override fun save(constituentFile: ConstituentFile): ConstituentFile {
        TODO("Not yet implemented")
    }

    override fun findSync(constituentID: String): ConstituentFile? {
        TODO("Not yet implemented")
    }
}