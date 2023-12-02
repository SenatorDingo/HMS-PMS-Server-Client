package seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.converter

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.UpdatePatientFileDTO
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.forms.actions.UpdatePatientFileForm

class UpdatePatientFileFormConverter {
    companion object {
        fun convertForm(updatePatientFileForm: UpdatePatientFileForm): UpdatePatientFileDTO {
            return UpdatePatientFileDTO(
                    updatePatientFileForm.medicalStaffId!!,
                    updatePatientFileForm.patientId!!,
                    updatePatientFileForm.insuranceNumber,
                    updatePatientFileForm.firstName,
                    updatePatientFileForm.lastName,
                    updatePatientFileForm.address,
                    updatePatientFileForm.telephoneNumber,
                    updatePatientFileForm.dateOfBirth,
                    updatePatientFileForm.gender,
                    updatePatientFileForm.maritalStatus,
                    updatePatientFileForm.externalDoctorId,
                    updatePatientFileForm.nextOfKinFirstName,
                    updatePatientFileForm.nextOfKinLastName,
                    updatePatientFileForm.nextOfKinRelationshipToPatient,
                    updatePatientFileForm.nextOfKinAddress,
                    updatePatientFileForm.nextOfKinTelephoneNumber
            )
        }
    }
}
