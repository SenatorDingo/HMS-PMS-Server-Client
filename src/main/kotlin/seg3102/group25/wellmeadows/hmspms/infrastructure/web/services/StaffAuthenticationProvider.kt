package seg3102.group25.wellmeadows.hmspms.infrastructure.web.services

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseStaffAccount
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.services.principles.StaffAccountPrinciple


@Service
class StaffAuthenticationProvider: AuthenticationProvider {

    @Autowired
    lateinit var staffAccountRepository: StaffAccountRepository
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

        if (username != null) {
            account = staffAccountRepository.findSync(username)
        }

        if(account != null ) {
            if(username == account!!.employeeNumber && passwordEncoder.matches(password, account!!.password)){
                val userRoles: Array<String> = account!!.type.map { it.name }.toTypedArray()
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