package seg3102.group25.wellmeadows.hmspms.infrastructure.web.services

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount


@Component
class StaffAuthenticationProvider: AuthenticationProvider {

    val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun authenticate(authentication: Authentication?): Authentication? {
        val username = authentication?.name
        val password = authentication?.credentials.toString()
        val userDetails: UserDetails? = isValidUser(username, password)
        if (userDetails != null) {
            return UsernamePasswordAuthenticationToken(username, password, userDetails.authorities)
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
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("staffAccounts").orderByChild("staffNumber").equalTo(username)

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

        if(staffAccount != null ) {
            if(username == staffAccount!!.employeeNumber && passwordEncoder.matches(password, staffAccount!!.getPassword())){
                val userRoles = staffAccount!!.getTypes().map { it.name }.toTypedArray()
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