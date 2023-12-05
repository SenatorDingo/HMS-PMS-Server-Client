package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions

import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType

class RegisterStaffForm {
    var employeeNumber: String? = null
    var password: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var emailAddress: String? = null
    var role: StaffType? = null
}