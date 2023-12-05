package seg3102.group25.wellmeadows.hmspms.adapters.factories

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientPrescriptionFactory
import seg3102.group25.wellmeadows.hmspms.domain.patient.valueObjects.PrescriptionType
import java.time.LocalDate

class PatientPrescriptionDtoFactory: PatientPrescriptionFactory {
    override fun createPatientPrescription(prescribeMedicationDTO: PrescribeMedicationDTO): PatientPrescription {
        return PatientPrescription(
            LocalDate.now().toString(),
            LocalDate.now().toString(),
            prescribeMedicationDTO.patientId + prescribeMedicationDTO.doctorId + prescribeMedicationDTO.drugName,
            PrescriptionType.Medication,
            prescribeMedicationDTO.doctorId,
            prescribeMedicationDTO.patientId,
            prescribeMedicationDTO.drugNumber,
            prescribeMedicationDTO.drugName,
            prescribeMedicationDTO.unitsPerDay,
            prescribeMedicationDTO.unitsAtAdministrationTimes,
            prescribeMedicationDTO.methodOfAdministration,
            prescribeMedicationDTO.startDate,
            prescribeMedicationDTO.finishDate
        )
    }
}