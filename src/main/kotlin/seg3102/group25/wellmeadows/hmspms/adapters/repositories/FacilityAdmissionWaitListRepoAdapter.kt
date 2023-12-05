package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityAdmissionWaitListRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseFacilityAdministrationWaitList

class FacilityAdmissionWaitListRepoAdapter: FacilityAdmissionWaitListRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList? {
        val existFile: FacilityAdmissionWaitList?
        runBlocking { existFile = find(facilityAdmissionWaitList) }
        return existFile
    }

    override suspend fun find(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList? {
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("facilityAdmissionWaitlist").orderByChild("patientId").equalTo(facilityAdmissionWaitList.patientId)

        val errorAccount = FacilityAdmissionWaitList("", "", "", "", "")
        val deferred = CompletableDeferred<FacilityAdmissionWaitList?>()

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
                    // Workaround - WORKS -
                    val facilityAdmissionWaitListThis = snapshot.children.firstOrNull()?.let { waitlistSnapshot ->
                        val admissionWaitList = DatabaseFacilityAdministrationWaitList()
                        waitlistSnapshot.children.forEach { dataWLC ->
                            when (dataWLC.key) {
                                "patientId" -> admissionWaitList.patientId = dataWLC.value as? String
                                "chargeNurseId" -> admissionWaitList.chargeNurseId = dataWLC.value as? String
                                "division" -> admissionWaitList.division = dataWLC.value as? String
                                "admissionStatus" -> admissionWaitList.admissionStatus = dataWLC.value as? String
                                "createdOn" -> admissionWaitList.createdOn = dataWLC.value as? String
                            }
                        }

                        admissionWaitList
                    }
                    val facilityDivision = FacilityAdmissionWaitList(
                        facilityAdmissionWaitListThis?.patientId ?: "",
                        facilityAdmissionWaitListThis?.chargeNurseId ?: "",
                        facilityAdmissionWaitListThis?.division ?: "",
                        facilityAdmissionWaitListThis?.admissionStatus ?: "",
                        facilityAdmissionWaitListThis?.createdOn ?: ""
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

    override fun findAll(): List<FacilityAdmissionWaitList> {
        //TODO("Not yet implemented")
        return mutableListOf() // Won't be used in this project
    }

    override fun save(facilityAdmissionWaitList: FacilityAdmissionWaitList): FacilityAdmissionWaitList {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("facilityAdmissionWaitlist").child(facilityAdmissionWaitList.patientId)
        newNode.setValueAsync(facilityAdmissionWaitList)
        return facilityAdmissionWaitList
    }
}