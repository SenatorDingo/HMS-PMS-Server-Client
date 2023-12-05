package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription.PatientPrescription
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientPrescriptionRepository
import seg3102.group25.wellmeadows.hmspms.domain.patient.valueObjects.PrescriptionType
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabasePatientPrescription

open class PatientPrescriptionRepoAdapter: PatientPrescriptionRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(prescriptionID: String): PatientPrescription? {
        val existAccount: PatientPrescription?
        runBlocking { existAccount = find(prescriptionID) }
        return existAccount
    }

    override suspend fun find(prescriptionID: String): PatientPrescription? {
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("patientPrescriptions").orderByChild("prescriptionID").equalTo(prescriptionID)

        val emptyListInt: MutableList<Int> = mutableListOf()
        val emptyListString: MutableList<String> = mutableListOf()

        val errorPrescription = PatientPrescription("", "", "", PrescriptionType.Error, "",
                "", "", "", -1, -1,
                "", "", "")
        val deferred = CompletableDeferred<PatientPrescription?>()

        val timeoutJob = CoroutineScope(Dispatchers.Default).launch {
            delay(10000) // Timeout after 5 seconds (adjust as needed)
            if (!deferred.isCompleted) { // Check if the deferred is not completed
                deferred.complete(errorPrescription) // Complete with errorAccount in case of timeout
            }
        }

        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                timeoutJob.cancel()
                deferred.complete(errorPrescription)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    //val firebasePatientPrescription = snapshot.getValue(DatabasePatientPrescription::class.java)
                    val firebasePatientPrescription = DatabasePatientPrescription()
                    snapshot.children.forEach { dataPP ->
                        when (dataPP.key) {
                            "createdOn" -> firebasePatientPrescription.createdOn = dataPP.value as? String
                            "updatedLast" -> firebasePatientPrescription.updatedLast = dataPP.value as? String
                            "prescriptionID" -> firebasePatientPrescription.prescriptionID = dataPP.value as? String
                            "prescriptionType" -> firebasePatientPrescription.prescriptionType = dataPP.getValue(PrescriptionType::class.java)
                            "doctorId" -> firebasePatientPrescription.doctorId = dataPP.value as? String
                            "patientId" -> firebasePatientPrescription.patientId = dataPP.value as? String
                            "drugNumber" -> firebasePatientPrescription.drugNumber = dataPP.value as? String
                            "drugName" -> firebasePatientPrescription.drugName = dataPP.value as? String
                            "unitsPerDay" -> firebasePatientPrescription.unitsPerDay = dataPP.getValue(Int::class.java)
                            "unitsAtAdministrationTimes" -> firebasePatientPrescription.unitsAtAdministrationTimes = dataPP.getValue(Int::class.java)
                            "methodOfAdministration" -> firebasePatientPrescription.methodOfAdministration = dataPP.value as? String
                            "startDate" -> firebasePatientPrescription.startDate = dataPP.value as? String
                            "finishDate" -> firebasePatientPrescription.finishDate = dataPP.value as? String
                        }
                    }
                    val patientPrescription = PatientPrescription(
                            firebasePatientPrescription.prescriptionID ?: "",
                            firebasePatientPrescription.updatedLast ?: "",
                            firebasePatientPrescription.prescriptionID ?: "",
                            firebasePatientPrescription.prescriptionType ?: PrescriptionType.Error,
                            firebasePatientPrescription.doctorId ?: "",
                            firebasePatientPrescription.patientId ?: "",
                            firebasePatientPrescription.drugNumber ?: "",
                            firebasePatientPrescription.drugName ?: "",
                            firebasePatientPrescription.unitsPerDay ?: -1,
                            firebasePatientPrescription.unitsAtAdministrationTimes ?: -1,
                            firebasePatientPrescription.methodOfAdministration ?: "",
                            firebasePatientPrescription.startDate ?: "",
                            firebasePatientPrescription.finishDate ?: ""
                    )
                    timeoutJob.cancel()
                    deferred.complete(patientPrescription)
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

    override fun save(patientPrescription: PatientPrescription): PatientPrescription {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
                .child("patientPrescriptions").child(patientPrescription.prescriptionID)
        newNode.setValueAsync(patientPrescription)
        return patientPrescription
    }
}