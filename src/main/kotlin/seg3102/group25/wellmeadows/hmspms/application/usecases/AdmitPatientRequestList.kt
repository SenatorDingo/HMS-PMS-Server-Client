package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientRequestListDTO

// Define an interface for admitting patients from the waiting list
interface AdmitPatientRequestList {
    fun admitPatientFromRequestList(staffNumber: String, admitPatientRequestListInfo: AdmitPatientRequestListDTO, admitPatientInfo: AdmitPatientDTO): Boolean
}