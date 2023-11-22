package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.AdmissionFactory

class AdmissionDtoFactory: AdmissionFactory {

    // TODO do we need this? Cross check with Admission Factory and DTOs
    override fun createAdmission(admitPatientInfo: AdmitPatientDTO): Admission {
        TODO("Not yet implemented")
    }

}