package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import seg3102.group25.wellmeadows.hmspms.domain.patient.entities.file.PatientFile
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientFileRepository
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount

class PatientFileRepoAdapter: PatientFileRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun find(patientNumber: String): PatientFile? {

        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("patientFiles").orderByChild("patientNumber").equalTo(patientNumber)

        var patientFile: PatientFile? = null

        uidRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError?) {
                println(error!!.message)
            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                // This returns the correct child...
                if (snapshot != null) {
                    if(snapshot.exists()) run {
                        val child = snapshot.children.firstOrNull()
                        if (child != null) {
                            patientFile = child.getValue(PatientFile::class.java)
                        }
                    }
                }
            }
        })

        return patientFile
    }

    override fun save(patientFile: PatientFile): PatientFile {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference.child("patientFiles").push()
        newNode.setValueAsync(patientFile)
        return patientFile
    }
}