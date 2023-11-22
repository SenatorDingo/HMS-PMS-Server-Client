package seg3102.group25.wellmeadows.hmspms.domain.facility.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RequestPatientAdmissionDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList

interface AdmissionFactory {
    fun createAdmission(admitPatientInfo: AdmitPatientDTO): Admission
}