package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientFileRepository
import seg3102.group25.wellmeadows.hmspms.domain.patient.valueObjects.PrescriptionType
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseConstituentFile
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseFacility
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabasePatientFile
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabasePatientPrescription

class PatientFileRepoAdapter: PatientFileRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(patientNumber: String): PatientFile? {
        val existFile: PatientFile?
        runBlocking { existFile = find(patientNumber) }
        return existFile
    }

    override fun savePrescription(patientFile: PatientFile,prescription: PatientPrescription): PatientFile {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
                .child("patientFiles").child(prescription.patientId).child("prescriptions").child(prescription.prescriptionID)
        newNode.setValueAsync(prescription)
        return patientFile

    }

    override suspend fun find(patientNumber: String): PatientFile? {
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("patientFiles").orderByChild("patientNumber").equalTo(patientNumber)

        val errorAccount = PatientFile("", "", "", "",
            "", "", "", "" , "", "",
            "", ConstituentFile(
                "", "", "", "", "", ""
            )
        )
        val deferred = CompletableDeferred<PatientFile?>()

        val timeoutJob = CoroutineScope(Dispatchers.Default).launch {
            delay(10000) // Timeout after 5 seconds (adjust as needed)
            if (!deferred.isCompleted) { // Check if the deferred is not completed
                deferred.complete(errorAccount) // Complete with errorAccount in case of timeout
            }
        }

        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                timeoutJob.cancel()
                deferred.complete(errorAccount)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    //val firebasePatientFile = snapshot.getValue(DatabasePatientFile::class.java)
                    // Workaround - WORKS -
                    val firebasePatientFile = snapshot.children.firstOrNull()?.let { staffSnapshot ->
                        val patientFile = DatabasePatientFile()
                        staffSnapshot.children.forEach { data ->
                            when (data.key) {
                                "patientNumber" -> patientFile.patientNumber = data.value as? String
                                "bedNumber" -> patientFile.bedNumber = data.value as? String
                                "firstName" -> patientFile.firstName = data.value as? String
                                "lastName" -> patientFile.lastName = data.value as? String
                                "admitted" -> patientFile.admitted = data.value as? Boolean == true
                                "address" -> patientFile.address = data.value as? String
                                "constituentFile" -> {
                                    val constituentFile = DatabaseConstituentFile()
                                    data.children.forEach { dataC ->
                                        when (dataC.key) {
                                            "address" -> constituentFile.address = dataC.value as? String
                                            "constituentID" -> constituentFile.constituentID = dataC.value as? String
                                            "firstName" -> constituentFile.firstName = dataC.value as? String
                                            "lastName" -> constituentFile.lastName = dataC.value as? String
                                            "relationship" -> constituentFile.relationship = dataC.value as? String
                                            "telephoneNumber" -> constituentFile.telephoneNumber = dataC.value as? String
                                        }
                                    }

                                    patientFile.constituentFile = ConstituentFile(
                                        constituentFile.constituentID ?: "",
                                        constituentFile.firstName ?: "",
                                        constituentFile.lastName ?: "",
                                        constituentFile.address ?: "",
                                        constituentFile.telephoneNumber ?: "",
                                        constituentFile.relationship ?: ""
                                    )

                                }
                                "division" -> {
                                    patientFile.division = data.value as? FacilityDivision
                                }
                                "dateOfBirth" -> patientFile.dateOfBirth = data.value as? String
                                "externalDoctorId" -> patientFile.externalDoctorId = data.value as? String
                                "gender" -> patientFile.gender = data.value as? String
                                "insuranceNumber" -> patientFile.insuranceNumber = data.value as? String
                                "localDoctor" -> patientFile.localDoctor = data.value as? String
                                "maritalStatus" -> patientFile.maritalStatus = data.value as? String
                                "medicalStaffId" -> patientFile.medicalStaffId = data.value as? String
                                "privateInsuranceNumber" -> patientFile.privateInsuranceNumber = data.value as? String
                                "roomNumber" -> patientFile.roomNumber = data.value as? String
                                "telephoneNumber" -> patientFile.telephoneNumber = data.value as? String
                                "prescriptions" -> {
                                    data.children.forEach { dataP ->
                                        val prescription = DatabasePatientPrescription()
                                        dataP.children.forEach { dataPP ->
                                            when (dataPP.key) {
                                                "createdOn" -> prescription.createdOn = dataPP.value as? String
                                                "updatedLast" -> prescription.updatedLast = dataPP.value as? String
                                                "prescriptionID" -> prescription.prescriptionID = dataPP.value as? String
                                                "prescriptionType" -> prescription.prescriptionType = dataPP.getValue(PrescriptionType::class.java)
                                                "doctorId" -> prescription.doctorId = dataPP.value as? String
                                                "patientId" -> prescription.patientId = dataPP.value as? String
                                                "drugNumber" -> prescription.drugNumber = dataPP.value as? String
                                                "drugName" -> prescription.drugName = dataPP.value as? String
                                                "unitsPerDay" -> prescription.unitsPerDay = dataPP.getValue(Int::class.java)
                                                "unitsAtAdministrationTimes" -> prescription.unitsAtAdministrationTimes = dataPP.getValue(Int::class.java)
                                                "methodOfAdministration" -> prescription.methodOfAdministration = dataPP.value as? String
                                                "startDate" -> prescription.startDate = dataPP.value as? String
                                                "finishDate" -> prescription.finishDate = dataPP.value as? String
                                            }
                                        }
                                        patientFile.prescriptions.add(
                                            PatientPrescription(
                                            prescription.createdOn ?: "",
                                                prescription.updatedLast ?: "",
                                                prescription.prescriptionID ?: "",
                                                prescription.prescriptionType ?: PrescriptionType.Error,
                                                prescription.doctorId ?: "",
                                                prescription.patientId ?: "",
                                                prescription.drugNumber ?: "",
                                                prescription.drugName ?: "",
                                                prescription.unitsPerDay ?: -1,
                                                prescription.unitsAtAdministrationTimes ?: -1,
                                                prescription.methodOfAdministration ?: "",
                                                prescription.startDate ?: "",
                                                prescription.finishDate ?: ""
                                        ))
                                    }
                                }
                            }
                        }

                        patientFile
                    }
                    val patientFile = PatientFile(
                        firebasePatientFile?.patientNumber ?: "",
                        firebasePatientFile?.medicalStaffId ?: "",
                        firebasePatientFile?.insuranceNumber ?: "",
                        firebasePatientFile?.firstName ?: "",
                        firebasePatientFile?.lastName ?: "",
                        firebasePatientFile?.address ?: "",
                        firebasePatientFile?.telephoneNumber ?: "",
                        firebasePatientFile?.dateOfBirth ?: "",
                        firebasePatientFile?.gender ?: "",
                        firebasePatientFile?.maritalStatus ?: "",
                        firebasePatientFile?.externalDoctorId ?: "",
                        firebasePatientFile?.constituentFile ?: ConstituentFile("", "", "", "", "", "")
                    )
                    patientFile.prescriptions = firebasePatientFile?.prescriptions ?: mutableListOf()
                    patientFile.admitted = firebasePatientFile?.admitted ?: false
                    patientFile.division = firebasePatientFile?.division
                    patientFile.localDoctor = firebasePatientFile?.localDoctor
                    patientFile.roomNumber = firebasePatientFile?.roomNumber
                    patientFile.bedNumber = firebasePatientFile?.bedNumber
                    patientFile.privateInsuranceNumber = firebasePatientFile?.privateInsuranceNumber

                    timeoutJob.cancel()
                    deferred.complete(patientFile)
                } else {
                    timeoutJob.cancel()
                    deferred.complete(null)
                }
            }
        }

        uidRef.addListenerForSingleValueEvent(valueEventListener)

        val result = deferred.await()

        // Remove the listener after getting the result or timeout
        uidRef.removeEventListener(valueEventListener)

        return result
    }

    override fun save(patientFile: PatientFile): PatientFile {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("patientFiles").child(patientFile.patientNumber)
        newNode.setValueAsync(patientFile)
        return patientFile
    }

}