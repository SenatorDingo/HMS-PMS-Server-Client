package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.springframework.stereotype.Repository
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository

@Repository
class StaffAccountRepoAdapter: StaffAccountRepository {

    override fun find(employeeNumber: String): StaffAccount? {
        TODO("Not yet implemented")
    }

    override fun save(staffAccount: StaffAccount): StaffAccount {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference.child("staffAccounts").push()
        val nodeKey: String = newNode.key
        newNode.setValueAsync(staffAccount)
        TODO("Not yet implemented")
    }
}