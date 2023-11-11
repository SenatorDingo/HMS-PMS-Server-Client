package seg3102.group25.wellmeadows.hmspms.domain.facility.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RequestPatientAdmissionDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList

interface FacilityAdmissionWaitListFactory {
    fun createAdmissionWaitList(requestPatientAdmissionInfo: RequestPatientAdmissionDTO): FacilityAdmissionWaitList
}