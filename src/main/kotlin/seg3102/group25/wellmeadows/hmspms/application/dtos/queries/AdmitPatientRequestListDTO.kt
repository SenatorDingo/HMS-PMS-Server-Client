package seg3102.group25.wellmeadows.hmspms.application.dtos.queries

import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType

data class AdmitPatientRequestListDTO(
    var patientId: String,
    var chargeNurseId: String,
    var divisionID: String,
    var admissionStatus: String,
)