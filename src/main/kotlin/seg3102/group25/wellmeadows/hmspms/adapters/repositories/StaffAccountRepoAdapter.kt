package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseStaffAccount

open class StaffAccountRepoAdapter: StaffAccountRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(employeeNumber: String): StaffAccount? {
        val existAccount: StaffAccount?
        runBlocking { existAccount = find(employeeNumber) }
        return existAccount
    }

    override suspend fun find(employeeNumber: String): StaffAccount? {
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("staffAccounts").orderByChild("employeeNumber").equalTo(employeeNumber)

        val errorAccount = StaffAccount("", "", "", "", "")
        val deferred = CompletableDeferred<StaffAccount?>()

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
                    val firebaseStaffAccount = snapshot.getValue(DatabaseStaffAccount::class.java)
                    val staffAccount = StaffAccount(
                        firebaseStaffAccount?.employeeNumber ?: "",
                        firebaseStaffAccount?.password ?: "",
                        firebaseStaffAccount?.firstName ?: "",
                        firebaseStaffAccount?.lastName ?: "",
                        firebaseStaffAccount?.emailAddress ?: ""
                    )
                    staffAccount.type = firebaseStaffAccount?.type ?: mutableListOf()
                    staffAccount.facilityID = firebaseStaffAccount?.facilityID ?: mutableListOf()
                    staffAccount.active = firebaseStaffAccount?.active ?: true
                    timeoutJob.cancel()
                    deferred.complete(staffAccount)
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

    override fun save(staffAccount: StaffAccount): StaffAccount {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("staffAccounts").child(staffAccount.employeeNumber)
        newNode.setValueAsync(staffAccount)
        return staffAccount
    }
}