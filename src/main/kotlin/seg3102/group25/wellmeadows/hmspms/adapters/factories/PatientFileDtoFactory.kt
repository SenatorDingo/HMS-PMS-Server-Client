package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientFileFactory

class PatientFileDtoFactory: PatientFileFactory {
    override fun createPatientFile(registerPatientDTO: RegisterPatientDTO): PatientFile {
        val constituentFile = ConstituentFile(
            registerPatientDTO.firstName+registerPatientDTO.lastName,
            registerPatientDTO.nextOfKinFirstName, registerPatientDTO.nextOfKinLastName,
            registerPatientDTO.nextOfKinAddress, registerPatientDTO.nextOfKinTelephoneNumber,
            registerPatientDTO.nextOfKinRelationshipToPatient
        )

        return PatientFile(registerPatientDTO.firstName+registerPatientDTO.lastName,
            registerPatientDTO.medicalStaffId, registerPatientDTO.insuranceNumber,
            registerPatientDTO.firstName, registerPatientDTO.lastName, registerPatientDTO.address,
            registerPatientDTO.telephoneNumber, registerPatientDTO.dateOfBirth, registerPatientDTO.gender,
            registerPatientDTO.maritalStatus, registerPatientDTO.externalDoctorId, constituentFile
            )
    }
}