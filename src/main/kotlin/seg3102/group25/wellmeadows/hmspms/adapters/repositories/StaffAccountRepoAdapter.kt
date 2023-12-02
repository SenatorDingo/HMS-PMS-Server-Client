package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Repository
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository

class StaffAccountRepoAdapter: StaffAccountRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun find(employeeNumber: String): StaffAccount? {

        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("staffAccounts").orderByChild("employeeNumber").equalTo(employeeNumber)

        var staffAccount: StaffAccount? = null

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
                            staffAccount = child.getValue(StaffAccount::class.java)
                        }
                    }
                }
            }
        })
        return staffAccount
    }

    override fun save(staffAccount: StaffAccount): StaffAccount {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("staffAccounts").child(staffAccount.employeeNumber)
        newNode.setValueAsync(staffAccount)
        return staffAccount
    }
}