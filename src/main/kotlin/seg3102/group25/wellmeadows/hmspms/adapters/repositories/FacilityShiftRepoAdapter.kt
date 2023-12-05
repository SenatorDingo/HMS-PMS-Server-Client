package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.ShiftRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.*

class FacilityShiftRepoAdapter: ShiftRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(shift: FacilityShift): FacilityShift? {
        val existFile: FacilityShift?
        runBlocking { existFile = find(shift) }
        return existFile
    }

    override suspend fun find(shift: FacilityShift): FacilityShift? {
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("facilityShifts").orderByChild("employeeNumber").equalTo(shift.staffNumber)

        val errorAccount = FacilityShift("", ShiftType.Early, "")
        val deferred = CompletableDeferred<FacilityShift?>()

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
                    val firebaseFacilityShift = snapshot.children.firstOrNull()?.let { staffSnapshot ->
                        val facilityShift = DatabaseFacilityShift()
                        staffSnapshot.children.forEach { data ->
                            when (data.key) {
                                "employeeNumber" -> facilityShift.staffNumber = data.value as? String
                                "shiftType" -> facilityShift.shiftType = data.getValue(ShiftType::class.java)
                                "division" -> facilityShift.division = data.value as? String
                            }
                        }

                        facilityShift
                    }

                    val facilityShift = FacilityShift(
                        firebaseFacilityShift?.staffNumber ?: "",
                        firebaseFacilityShift?.shiftType ?: ShiftType.Early,
                        firebaseFacilityShift?.division ?: "",
                    )
                    timeoutJob.cancel()
                    deferred.complete(facilityShift)
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

    override fun findAll(staffNumber: String): List<FacilityShift> {
        //TODO("Not yet implemented")
        return mutableListOf() // Won't be used in this project
    }

    override fun save(shift: FacilityShift): FacilityShift {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("facilityShifts").child(shift.staffNumber)
        newNode.setValueAsync(shift)
        return shift
    }

    override fun remove(shift: FacilityShift): Boolean {
        //TODO("Not yet implemented")
        return false // Won't be used in this project
    }
}