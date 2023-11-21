package seg3102.group25.wellmeadows.hmspms.infrastructure.database.services

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount

class StaffAccountService {

    val COL_ID = "staffs"

    @Throws(InterruptedException::class, Exception::class)
    fun saveStaffDetails(staffAccount: StaffAccount): String? {
        val dbFirestore: Firestore = FirestoreClient.getFirestore()
        val collectionsApiFuture: ApiFuture<WriteResult> =
            dbFirestore.collection(COL_ID).document(staffAccount.employeeNumber).set(staffAccount)
        return collectionsApiFuture.get().getUpdateTime().toString()
    }

    @Throws(InterruptedException::class, Exception::class)
    fun getPatientDetails(name: String?): StaffAccount? {
        val dbFirestore: Firestore = FirestoreClient.getFirestore()
        val documentReference = dbFirestore.collection(COL_ID).document(name!!)
        val future = documentReference.get()
        val document = future.get()
        var staffAccount: StaffAccount? = null
        return if (document.exists()) {
            staffAccount = document.toObject(StaffAccount::class.java)
            staffAccount
        } else {
            null
        }
    }

    @Throws(InterruptedException::class, Exception::class)
    fun updateStaffDetails(staffAccount: StaffAccount): String? {
        val dbFirestore: Firestore = FirestoreClient.getFirestore()
        val collectionsApiFuture: ApiFuture<WriteResult> =
            dbFirestore.collection(COL_ID).document(staffAccount.employeeNumber).set(staffAccount)
        return collectionsApiFuture.get().updateTime.toString()
    }

    fun deletePatient(name: String): String {
        val dbFirestore: Firestore = FirestoreClient.getFirestore()
        val writeResult: ApiFuture<WriteResult> = dbFirestore.collection(COL_ID).document(name).delete()
        return "Document with Patient ID $name has been deleted"
    }
}