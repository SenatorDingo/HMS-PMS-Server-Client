package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RequestPatientAdmissionDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityAdmissionWaitListFactory
import java.time.LocalDate

class FacilityAdmissionWaitListDtoFactory: FacilityAdmissionWaitListFactory {
    override fun createAdmissionWaitList(requestPatientAdmissionInfo: RequestPatientAdmissionDTO): FacilityAdmissionWaitList {
        return FacilityAdmissionWaitList(
            requestPatientAdmissionInfo.patientId,
            requestPatientAdmissionInfo.chargeNurseId,
            requestPatientAdmissionInfo.divisionId,
            requestPatientAdmissionInfo.rationaleForRequest,
            requestPatientAdmissionInfo.priorityAssessment,
            LocalDate.now().toString()
        )
    }
}