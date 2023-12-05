package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientFileRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabasePatientFile

class PatientFileRepoAdapter: PatientFileRepository {


    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(patientNumber: String): PatientFile? {
        val existFile: PatientFile?
        runBlocking { existFile = find(patientNumber) }
        return existFile
    }

    override fun savePrescription(patientFile: PatientFile,prescription: PatientPrescription): PatientFile? {
        val patientID: String = patientFile.patientNumber
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
                .child("patientFiles").child(patientID).child("prescriptions")
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
                    val firebasePatientFile = snapshot.getValue(DatabasePatientFile::class.java)
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