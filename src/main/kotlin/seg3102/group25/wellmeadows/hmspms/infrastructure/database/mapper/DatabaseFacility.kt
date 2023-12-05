package seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper

import com.google.firebase.database.PropertyName
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityStatus
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType

class DatabaseFacility(
    @get:PropertyName("divisionId")
    @set:PropertyName("divisionId")
    var divisionId: String? = null,

    @get:PropertyName("divisionName")
    @set:PropertyName("divisionName")
    var divisionName: String? = null,

    @get:PropertyName("chargeNurseFirstName")
    @set:PropertyName("chargeNurseFirstName")
    var chargeNurseFirstName: String? = null,

    @get:PropertyName("chargeNurseLastName")
    @set:PropertyName("chargeNurseLastName")
    var chargeNurseLastName: String? = null,

    @get:PropertyName("chargeNurseTelExtension")
    @set:PropertyName("chargeNurseTelExtension")
    var chargeNurseTelExtension: String? = null,

    @get:PropertyName("chargeNurseBipExtension")
    @set:PropertyName("chargeNurseBipExtension")
    var chargeNurseBipExtension: String? = null,

    @get:PropertyName("location")
    @set:PropertyName("location")
    var location: String? = null,

    @get:PropertyName("numberBeds")
    @set:PropertyName("numberBeds")
    var numberBeds: Int? = null,

    @get:PropertyName("telephoneExtension")
    @set:PropertyName("telephoneExtension")
    var telephoneExtension: String? = null,

    @get:PropertyName("facilityType")
    @set:PropertyName("facilityType")
    var facilityType: FacilityType? = FacilityType.None,

    @get:PropertyName("numberBedsAvailable")
    @set:PropertyName("numberBedsAvailable")
    var numberBedsAvailable: Int? = numberBeds,

    @get:PropertyName("status")
    @set:PropertyName("status")
    var status: FacilityStatus = FacilityStatus.Incomplete,

    @get:PropertyName("shifts")
    @set:PropertyName("shifts")
    var shifts: MutableList<FacilityShift>? = mutableListOf(),

    @get:PropertyName("admissionWaitList")
    @set:PropertyName("admissionWaitList")
    var admissionWaitList: MutableList<FacilityAdmissionWaitList>? = mutableListOf(),

    @get:PropertyName("admissions")
    @set:PropertyName("admissions")
    var admissions: MutableList<Admission>? = mutableListOf()
    )