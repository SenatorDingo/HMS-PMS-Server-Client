package seg3102.group25.wellmeadows.hmspms.contracts.testStubs.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RequestPatientAdmissionDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityAdmissionWaitListFactory
import java.util.Date

class FacilityAdmissionWaitListFactoryStub : FacilityAdmissionWaitListFactory {
    // placeholder date
    var date = Date()
    
    override fun createAdmissionWaitList(requestPatientAdmissionInfo: RequestPatientAdmissionDTO): FacilityAdmissionWaitList {
        return FacilityAdmissionWaitList(
            requestPatientAdmissionInfo.patientId,
            requestPatientAdmissionInfo.chargeNurseId,
            requestPatientAdmissionInfo.divisionId,
            "",
            date
        )
    }
}
