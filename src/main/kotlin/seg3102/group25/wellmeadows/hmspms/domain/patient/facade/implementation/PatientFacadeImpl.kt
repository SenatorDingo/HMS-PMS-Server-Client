package seg3102.group25.wellmeadows.hmspms.domain.patient.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.AdmitPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.PrescribeMedicationDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.UpdatePatientFileDTO
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityAdmissionRepository
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.events.*
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientFileFactory
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientPrescriptionFactory
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientFileRepository
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientPrescriptionRepository
import java.util.*

class PatientFacadeImpl(
    private val patientFileRepository: PatientFileRepository,
    private val patientPrescriptionRepository: PatientPrescriptionRepository,
    private val patientFileFactory: PatientFileFactory,
    private val patientPrescriptionFactory: PatientPrescriptionFactory,
    private val facilityAdmissionRepoAdapter: FacilityAdmissionRepository,
    private val eventEmitter: DomainEventEmitter
): PatientFacade {
    override fun createPatientFile(patientDTO: RegisterPatientDTO): Boolean {
        val patient = patientFileFactory.createPatientFile(patientDTO)
        val existAccount = patientFileRepository.findSync(patient.patientNumber)
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

    override fun updatePatientFile(updatePatientDTO: UpdatePatientFileDTO): Boolean {

        val existAccount = patientFileRepository.findSync(updatePatientDTO.patientId)
        if (existAccount != null){
            existAccount.update(updatePatientDTO)
            patientFileRepository.save(existAccount)
            eventEmitter.emit(
                PatientFileUpdated(
                    UUID.randomUUID(),
                    Date(),
                    existAccount.patientNumber
                )
            )
            return true
        }
        return false
    }

    override fun getPatientFile(patientNumber: String): PatientFile? {
        return patientFileRepository.findSync(patientNumber)
    }

    override fun createPatientPrescription(prescriptionDTO: PrescribeMedicationDTO): Boolean {
        val prescription = patientPrescriptionFactory.createPatientPrescription(prescriptionDTO)
        val patient = patientFileRepository.findSync(prescription.patientId)
        val existAccount = patientPrescriptionRepository.findSync(prescription.prescriptionID)
        if (existAccount != null){
            return false
        }
        if(patient != null){
            patient.addPrescription(prescription)
            patientFileRepository.savePrescription(patient,prescription)
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
        val patient = patientFileRepository.findSync(prescription.patientId)
        val existAccount = patientPrescriptionRepository.findSync(prescription.prescriptionID)
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
        val existAccount = patientFileRepository.findSync(patientNumber)
        if (existAccount != null){
           prescriptions = existAccount.prescriptions
        }
        return prescriptions
    }

    override fun admitPatient(admitPatientInfo: AdmitPatientDTO, patientNumber: String, division: FacilityDivision): Boolean {

        val existAccount = patientFileRepository.findSync(patientNumber)
        if (existAccount != null){
            val admitted: Admission? = facilityAdmissionRepoAdapter.findSync(patientNumber, admitPatientInfo.roomNumber)
            if (admitted == null){
                val admission = Admission(admitPatientInfo.patientNumber,
                        admitPatientInfo.localDoctor,
                        admitPatientInfo.roomNumber,
                        admitPatientInfo.bedNumber,
                        admitPatientInfo.privateInsuranceNumber)
                facilityAdmissionRepoAdapter.save(admission)
                return true
            }

        }
        return false
        /*

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

         */


    }

    override fun dischargePatient(patientNumber: String, divisionID:String): Boolean {
        val existAccount = patientFileRepository.findSync(patientNumber)
        if (existAccount != null){
            facilityAdmissionRepoAdapter.remove(patientNumber, divisionID)
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