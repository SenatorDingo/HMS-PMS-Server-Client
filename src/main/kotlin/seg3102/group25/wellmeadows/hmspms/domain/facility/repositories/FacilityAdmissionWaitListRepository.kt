package seg3102.group25.wellmeadows.hmspms.domain.facility.repositories

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList

interface FacilityAdmissionWaitListRepository {
    suspend fun find(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList?
    fun findAll(): List<FacilityAdmissionWaitList>
    fun save(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList
    fun findSync(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList?
}