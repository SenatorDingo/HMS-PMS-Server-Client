package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.repositories

import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientFileRepository

class PatientFileRepositoryStub : PatientFileRepository {
    override suspend fun find(patientNumber: String): PatientFile? {
        TODO("Not yet implemented")
    }

    override fun save(patientFile: PatientFile): PatientFile {
        TODO("Not yet implemented")
    }

    override fun findSync(patientNumber: String): PatientFile? {
        TODO("Not yet implemented")
    }
}