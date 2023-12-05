package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.constituent.repositories.ConstituentFileRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseConstituentFile

class ConstituentFileRepoAdapter: ConstituentFileRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(constituentID: String): ConstituentFile? {
        val existFile: ConstituentFile?
        runBlocking { existFile = find(constituentID) }
        return existFile
    }

    override suspend fun find(constituentID: String): ConstituentFile? {
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("constituentFiles").orderByChild("constituentID").equalTo(constituentID)

        val errorAccount = ConstituentFile("", "", "", "",
            "", ""
        )
        val deferred = CompletableDeferred<ConstituentFile?>()

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
                    val firebaseConstituentFile = snapshot.children.firstOrNull()?.let { constituentSnapshot ->
                        val constituentFile = DatabaseConstituentFile()
                        constituentSnapshot.children.forEach { dataC ->
                            when (dataC.key) {
                                "address" -> constituentFile.address = dataC.value as? String
                                "constituentID" -> constituentFile.constituentID = dataC.value as? String
                                "firstName" -> constituentFile.firstName = dataC.value as? String
                                "lastName" -> constituentFile.lastName = dataC.value as? String
                                "relationship" -> constituentFile.relationship = dataC.value as? String
                                "telephoneNumber" -> constituentFile.telephoneNumber = dataC.value as? String
                            }
                        }

                        constituentFile
                    }
                    val constituentFile = ConstituentFile(
                        firebaseConstituentFile?.constituentID ?: "",
                        firebaseConstituentFile?.firstName ?: "",
                        firebaseConstituentFile?.lastName ?: "",
                        firebaseConstituentFile?.address ?: "",
                        firebaseConstituentFile?.telephoneNumber ?: "",
                        firebaseConstituentFile?.relationship ?: "",
                        )

                    timeoutJob.cancel()
                    deferred.complete(constituentFile)
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

    override fun save(constituentFile: ConstituentFile): ConstituentFile {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("constituentFiles").child(constituentFile.constituentID)
        newNode.setValueAsync(constituentFile)
        return constituentFile
    }

}