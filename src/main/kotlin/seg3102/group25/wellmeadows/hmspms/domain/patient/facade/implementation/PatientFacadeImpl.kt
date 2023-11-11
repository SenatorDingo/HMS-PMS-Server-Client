package seg3102.group25.wellmeadows.hmspms.domain.patient.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.events.*
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientFileFactory
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientPrescriptionFactory
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientFileRepository
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientPrescriptionRepository
import java.util.*
import kotlin.collections.ArrayList

class PatientFacadeImpl(
    private val patientFileRepository: PatientFileRepository,
    private val patientPrescriptionRepository: PatientPrescriptionRepository,
    private val patientFileFactory: PatientFileFactory,
    private val patientPrescriptionFactory: PatientPrescriptionFactory,
    private val eventEmitter: DomainEventEmitter
): PatientFacade {
    override fun createPatientFile(patientDTO: RegisterPatientDTO): Boolean {
        val patient = patientFileFactory.createPatientFile(patientDTO)
        val existAccount = patientFileRepository.find(patient.patientNumber)
        if (existAccount != null){
            return false
        }
        patientFileRepository.save(patient)
        eventEmitter.emit(
            PatientFileRegistered(
                UUID.randomUUID(),
                Date(),
                patient.patientNumber
            )
        )
        return true
    }

    override fun updatePatientFile(patientDTO: RegisterPatientDTO): Boolean {
        val patient = patientFileFactory.createPatientFile(patientDTO)
        val existAccount = patientFileRepository.find(patient.patientNumber)
        if (existAccount != null){
            existAccount.update(patient)
            patientFileRepository.save(patient)
            eventEmitter.emit(
                PatientFileUpdated(
                    UUID.randomUUID(),
                    Date(),
                    patient.patientNumber
                )
            )
            return true
        }
        return false
    }

    override fun getPatientFile(patientNumber: String): PatientFile? {
        return patientFileRepository.find(patientNumber)
    }

    override fun createPatientPrescription(prescriptionDTO: PrescribeMedicationDTO): Boolean {
        val prescription = patientPrescriptionFactory.createPatientPrescription(prescriptionDTO)
        val patient = patientFileRepository.find(prescription.patientId)
        val existAccount = patientPrescriptionRepository.find(prescription.prescriptionID)
        if (existAccount != null){
            return false
        }
        if(patient != null){
            patient.addPrescription(prescription)
            patientFileRepository.save(patient)
            patientPrescriptionRepository.save(prescription)
            eventEmitter.emit(
                PatientPrescriptionCreated(
                    UUID.randomUUID(),
                    Date(),
                    prescription.prescriptionID
                )
            )
            return true
        }
        return false
    }

    override fun updatePatientPrescription(prescriptionDTO: PrescribeMedicationDTO): Boolean {
        val prescription = patientPrescriptionFactory.createPatientPrescription(prescriptionDTO)
        val patient = patientFileRepository.find(prescription.patientId)
        val existAccount = patientPrescriptionRepository.find(prescription.prescriptionID)
        if (existAccount != null){
            if(patient != null){
                patient.updatePrescription(prescription)
                patientFileRepository.save(patient)
                patientPrescriptionRepository.save(prescription)
                eventEmitter.emit(
                    PatientPrescriptionUpdated(
                        UUID.randomUUID(),
                        Date(),
                        prescription.prescriptionID
                    )
                )
                return true
            }
        }
        return false
    }

    override fun getPatientPrescriptions(patientNumber: String): List<PatientPrescription> {
        var prescriptions: List<PatientPrescription> = ArrayList()
        val existAccount = patientFileRepository.find(patientNumber)
        if (existAccount != null){
           prescriptions = existAccount.getPrescriptions()
        }
        return prescriptions
    }

    override fun admitPatient(admitPatientInfo: AdmitPatientDTO, patientNumber: String, division: FacilityDivision): Boolean {
        val existAccount = patientFileRepository.find(patientNumber)
        if (existAccount != null){
            existAccount.admit(
                admitPatientInfo.localDoctor,
                admitPatientInfo.roomNumber,
                admitPatientInfo.bedNumber,
                admitPatientInfo.privateInsuranceNumber,
                division)
            eventEmitter.emit(
                PatientAdmitted(
                    UUID.randomUUID(),
                    Date(),
                    patientNumber
                )
            )
            return true
        }
        return false
    }

    override fun dischargePatient(patientNumber: String): Boolean {
        val existAccount = patientFileRepository.find(patientNumber)
        if (existAccount != null){
            existAccount.discharge()
            eventEmitter.emit(
                PatientDischarged(
                    UUID.randomUUID(),
                    Date(),
                    patientNumber
                )
            )
            return true
        }
        return false
    }
}