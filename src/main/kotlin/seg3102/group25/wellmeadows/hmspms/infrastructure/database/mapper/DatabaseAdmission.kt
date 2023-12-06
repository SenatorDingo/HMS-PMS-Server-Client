package seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper

import com.google.firebase.database.PropertyName

class DatabaseAdmission {

    @get:PropertyName("patientNumber")
    @set:PropertyName("patientNumber")
    var patientNumber: String? = null

    @get:PropertyName("localDoctor")
    @set:PropertyName("localDoctor")
    var localDoctor: String? = null


    @get:PropertyName("admissionStatus")
    @set:PropertyName("admissionStatus")
    var roomNumber: String? = null

    @get:PropertyName("priority")
    @set:PropertyName("priority")
    var bedNumber: String? = null

    @get:PropertyName("privateInsuranceNumber")
    @set:PropertyName("privateInsuranceNumber")
    var privateInsuranceNumber: String? = null
}