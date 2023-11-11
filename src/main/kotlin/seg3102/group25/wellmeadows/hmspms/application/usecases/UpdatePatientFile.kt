package seg3102.group25.wellmeadows.hmspms.application.usecases

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.UpdatePatientFileDTO

// Define an interface for updating patient information
interface UpdatePatientFile {
    fun updatePatientFile(staffNumber: String, updatePatientFileDTO: UpdatePatientFileDTO): Boolean
}