package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.constituent.repositories.ConstituentFileRepository

class ConstituentFileRepoAdapter: ConstituentFileRepository {
    override fun find(constituentID: String): ConstituentFile? {
        TODO("Not yet implemented")
    }

    override fun save(constituentFile: ConstituentFile): ConstituentFile {
        TODO("Not yet implemented")
    }

}