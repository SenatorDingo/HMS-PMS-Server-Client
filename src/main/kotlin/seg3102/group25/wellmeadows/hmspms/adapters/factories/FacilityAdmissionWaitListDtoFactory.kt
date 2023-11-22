package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RequestPatientAdmissionDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityAdmissionWaitListFactory

class FacilityAdmissionWaitListDtoFactory: FacilityAdmissionWaitListFactory {
    override fun createAdmissionWaitList(requestPatientAdmissionInfo: RequestPatientAdmissionDTO): FacilityAdmissionWaitList {
        TODO("Not yet implemented")
    }
}