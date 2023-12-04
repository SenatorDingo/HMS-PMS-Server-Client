package seg3102.group25.wellmeadows.hmspms.domain.patient.repositories

import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile

interface PatientFileRepository {
    suspend fun find(patientNumber: String): PatientFile?
    fun save(patientFile: PatientFile): PatientFile
    fun findSync(patientNumber: String): PatientFile?
}