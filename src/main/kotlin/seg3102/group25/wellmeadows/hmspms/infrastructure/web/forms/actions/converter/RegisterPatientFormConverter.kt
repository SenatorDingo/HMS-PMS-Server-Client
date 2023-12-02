package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.RegisterPatientForm

class RegisterPatientFormConverter {
    companion object{
        fun convertForm(registerPatientForm: RegisterPatientForm): RegisterPatientDTO {
            return RegisterPatientDTO(
                registerPatientForm.medicalStaffId!!,
                registerPatientForm.insuranceNumber!!,
                registerPatientForm.firstName!!,
                registerPatientForm.lastName!!,
                registerPatientForm.address!!,
                registerPatientForm.telephoneNumber!!,
                registerPatientForm.dateOfBirth!!,
                registerPatientForm.gender!!,
                registerPatientForm.maritalStatus!!,
                registerPatientForm.externalDoctorId!!,
                registerPatientForm.nextOfKinFirstName!!,
                registerPatientForm.nextOfKinLastName!!,
                registerPatientForm.nextOfKinRelationshipToPatient!!,
                registerPatientForm.nextOfKinAddress!!,
                registerPatientForm.nextOfKinTelephoneNumber!!
            )
        }
    }
}