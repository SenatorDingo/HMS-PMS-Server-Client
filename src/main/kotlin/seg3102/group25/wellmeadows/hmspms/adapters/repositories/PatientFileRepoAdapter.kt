package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientFileRepository
import java.time.LocalDate

class PatientFileRepoAdapter: PatientFileRepository {


    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(patientNumber: String): PatientFile? {
        val existFile: PatientFile?
        runBlocking { existFile = find(patientNumber) }
        return existFile
    }

    override suspend fun find(patientNumber: String): PatientFile? {
        val ref: DatabaseReference = dataBase.getReference("patientFiles")
        val uidRef = ref.child(patientNumber)

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
                println("ERROR")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val patientFile = snapshot.getValue(PatientFile::class.java)
                    timeoutJob.cancel()
                    deferred.complete(patientFile)
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

    override fun save(patientFile: PatientFile): PatientFile {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("patientFiles").child(patientFile.patientNumber)
        newNode.setValueAsync(patientFile)
        return patientFile
    }
}