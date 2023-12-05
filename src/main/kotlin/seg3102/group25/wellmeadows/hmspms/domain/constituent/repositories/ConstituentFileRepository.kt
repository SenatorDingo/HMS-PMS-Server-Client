package seg3102.group25.wellmeadows.hmspms.domain.constituent.repositories

import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile

interface ConstituentFileRepository {
    suspend fun find(constituentID: String): ConstituentFile?
    fun save(constituentFile: ConstituentFile): ConstituentFile
    fun findSync(constituentID: String): ConstituentFile?
}