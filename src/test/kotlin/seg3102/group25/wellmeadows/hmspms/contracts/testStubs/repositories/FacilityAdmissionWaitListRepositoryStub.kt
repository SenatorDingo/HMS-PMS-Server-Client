package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.repositories

import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityAdmissionWaitListRepository

class FacilityAdmissionWaitListRepositoryStub : FacilityAdmissionWaitListRepository {
    override suspend fun find(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<FacilityAdmissionWaitList> {
        TODO("Not yet implemented")
    }

    override fun save(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList {
        TODO("Not yet implemented")
    }

    override fun findSync(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList? {
        TODO("Not yet implemented")
    }
}