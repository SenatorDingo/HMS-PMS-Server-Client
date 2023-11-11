package seg3102.group25.wellmeadows.hmspms.domain.patient.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile

interface PatientFileFactory {
    fun createPatientFile(registerPatientDTO: RegisterPatientDTO): PatientFile
}