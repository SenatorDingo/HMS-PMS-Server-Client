package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientFileFactory

class PatientFileDtoFactory: PatientFileFactory {
    override fun createPatientFile(registerPatientDTO: RegisterPatientDTO): PatientFile {
        TODO("Not yet implemented")
    }
}