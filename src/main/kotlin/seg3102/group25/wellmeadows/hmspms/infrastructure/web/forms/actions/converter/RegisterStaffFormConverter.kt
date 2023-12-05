package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.RegisterStaffForm

class RegisterStaffFormConverter {
    companion object {
        fun convertForm(registerStaffForm: RegisterStaffForm): RegisterStaffDTO {
            return RegisterStaffDTO(
                    registerStaffForm.employeeNumber!!,
                    registerStaffForm.password!!,
                    registerStaffForm.firstName!!,
                    registerStaffForm.lastName!!,
                    registerStaffForm.emailAddress!!
            )
        }

    }
}
