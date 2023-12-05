package seg3102.group25.wellmeadows.hmspms.infrastructure.web.services

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseStaffAccount
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.services.principles.StaffAccountPrinciple


@Service
class StaffAuthenticationProvider: AuthenticationProvider {

    val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var account: StaffAccount? = null

    override fun authenticate(authentication: Authentication?): Authentication? {
        val username = authentication?.name
        val password = authentication?.credentials.toString()
        val userDetails: UserDetails? = isValidUser(username, password)
        if (userDetails != null) {
            val customPrincipal = StaffAccountPrinciple(account, account!!.employeeNumber)
            return UsernamePasswordAuthenticationToken(
                customPrincipal,
                password, // Assuming password here, replace it with actual credentials
                userDetails.authorities
            )
        }
        return authentication
    }

    override fun supports(authentication: Class<*>?): Boolean {
        if (authentication != null) {
            return authentication == UsernamePasswordAuthenticationToken::class.java
        }
        return false
    }

    private fun isValidUser(username: String?, password: String): UserDetails? {
        val passwordEncoder = BCryptPasswordEncoder()

        runBlocking {
            val ref: DatabaseReference = dataBase.reference
            val uidRef = ref.child("staffAccounts").orderByChild("employeeNumber").equalTo(username)

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

            account = deferred.await()

            // Remove the listener after getting the result or timeout
            uidRef.removeEventListener(valueEventListener)
        }


        if(account != null ) {
            if(username == account!!.employeeNumber && passwordEncoder.matches(password, account!!.password)){
                val userRoles = account!!.type!!.map { it.name }.toTypedArray()
                return User
                        .withUsername(username)
                        .password(passwordEncoder.encode(password))
                        .roles(*userRoles)
                        .build()
                }
        }

        return null
    }
}