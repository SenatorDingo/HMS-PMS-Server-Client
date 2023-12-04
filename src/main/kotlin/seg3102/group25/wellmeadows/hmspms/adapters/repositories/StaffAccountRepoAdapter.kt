package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository

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
                println("ERROR")
                timeoutJob.cancel()
                deferred.complete(errorAccount)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    println("BIG MAYBE?")
                    val staffAccount = snapshot.getValue(StaffAccount::class.java)
                    println("CREATED??")
                    timeoutJob.cancel()
                    deferred.complete(staffAccount)
                } else {
                    println("HERE MAYBE?")
                    timeoutJob.cancel()
                    deferred.complete(null)
                }
            }
        }

        println("Here1")

        uidRef.addListenerForSingleValueEvent(valueEventListener)

        println("Here2")

        val result = deferred.await()

        println("Here3")

        // Remove the listener after getting the result or timeout
        uidRef.removeEventListener(valueEventListener)

        println("Here4")

        return result
    }

    override fun save(staffAccount: StaffAccount): StaffAccount {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("staffAccounts").child(staffAccount.employeeNumber)
        newNode.setValueAsync(staffAccount)
        return staffAccount
    }
}