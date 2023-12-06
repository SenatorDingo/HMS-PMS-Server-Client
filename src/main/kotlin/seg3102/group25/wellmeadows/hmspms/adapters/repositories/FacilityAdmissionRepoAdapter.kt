package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityAdmissionRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseAdmission

class FacilityAdmissionRepoAdapter: FacilityAdmissionRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override suspend fun find(patientID: String, divisionID: String): Admission? {
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("admissions").child(divisionID).orderByChild("patientNumber").equalTo(patientID)

        val errorAccount = Admission("", "", "", "", "")
        val deferred = CompletableDeferred<Admission?>()

        val timeoutJob = CoroutineScope(Dispatchers.Default).launch {
            delay(20000) // Timeout after 5 seconds (adjust as needed)
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
                    //val firebaseStaffAccount = snapshot.getValue(DatabaseStaffAccount::class.java)
                    // Workaround - WORKS -
                    val admissionThis = snapshot.children.firstOrNull()?.let { waitlistSnapshot ->
                        val admission = DatabaseAdmission()
                        waitlistSnapshot.children.forEach { dataWLC ->
                            when (dataWLC.key) {
                                "patientNumber" -> admission.patientNumber = dataWLC.value as? String
                                "localDoctor" -> admission.localDoctor = dataWLC.value as? String
                                "bedNumber" -> admission.bedNumber = dataWLC.value as? String
                                "privateInsuranceNumber" -> admission.privateInsuranceNumber = dataWLC.value as? String
                                "roomNumber" -> admission.roomNumber = dataWLC.value as? String
                            }
                        }

                        admission
                    }
                    val facilityDivision = Admission(
                            admissionThis?.patientNumber ?: "",
                            admissionThis?.localDoctor ?: "",
                            admissionThis?.roomNumber ?: "",
                            admissionThis?.bedNumber ?: "",
                            admissionThis?.privateInsuranceNumber ?:""
                    )
                    timeoutJob.cancel()
                    deferred.complete(facilityDivision)
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

    override suspend fun findAll(divisionID: String): List<Admission>? {


        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("admissions").orderByChild("roomNumber").equalTo(divisionID)

        val errorAccount = mutableListOf(Admission("", "", "", "", ""))
        val deferred = CompletableDeferred<List<Admission>?>()

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
                    //val firebaseStaffAccount = snapshot.getValue(DatabaseStaffAccount::class.java)
                    val list: MutableList<Admission> = mutableListOf()
                    // Workaround - WORKS -
                    snapshot.children.forEach {
                        val admissionThis = DatabaseAdmission()
                        it.children.forEach { dataWLC ->
                            when (dataWLC.key) {
                                "patientNumber" -> admissionThis.patientNumber = dataWLC.value as? String
                                "localDoctor" -> admissionThis.localDoctor = dataWLC.value as? String
                                "bedNumber" -> admissionThis.bedNumber = dataWLC.value as? String
                                "privateInsuranceNumber" -> admissionThis.privateInsuranceNumber = dataWLC.value as? String
                                "roomNumber" -> admissionThis.roomNumber = dataWLC.value as? String
                            }
                        }

                        list.add(Admission(
                                admissionThis?.patientNumber ?: "",
                                admissionThis?.localDoctor ?: "",
                                admissionThis?.roomNumber ?: "",
                                admissionThis?.bedNumber ?: "",
                                admissionThis?.privateInsuranceNumber ?:""
                        ))
                    }

                    timeoutJob.cancel()
                    deferred.complete(list)
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

    override fun save(admission: Admission): Admission {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
                .child("admissions").child(admission.roomNumber).child(admission.patientNumber)
        newNode.setValueAsync(admission)
        return admission
    }

    override fun findSync(patientID: String, divisionID: String): Admission? {
        val existFile: Admission?
        runBlocking { existFile = find(patientID, divisionID) }
        return existFile
    }

    override fun remove(patientID: String, divisionID: String): Boolean {

        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
                .child("admissions").child(divisionID).child(patientID)
        newNode.setValueAsync(null)
        return true
    }

    override fun findSyncAll(divisionID: String): List<Admission>? {
        val existFile: List<Admission>?
        runBlocking { existFile = findAll(divisionID) }
        return existFile
    }

}