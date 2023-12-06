package seg3102.group25.wellmeadows.hmspms.domain.facility.repositories

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission

interface FacilityAdmissionRepository {
    suspend fun find(patientID: String, divisionID: String): Admission?
    suspend fun findAll(divisionID: String): List<Admission>?
    fun save(admission: Admission): Admission
    fun findSync(patientID: String, divisionID: String): Admission?
    fun remove(patientID: String, divisionID: String): Boolean
    fun findSyncAll(divisionID: String): List<Admission>?
}