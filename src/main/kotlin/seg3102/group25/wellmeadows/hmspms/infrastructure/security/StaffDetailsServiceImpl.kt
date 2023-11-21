package seg3102.group25.wellmeadows.hmspms.infrastructure.security

import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UsernameNotFoundException
import seg3102.group25.wellmeadows.hmspms.infrastructure.jpa.dao.StaffJpaRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.security.credentials.Staff

@Service
class StaffDetailsServiceImpl(val staffRepository: StaffJpaRepository): UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: Staff = staffRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User username: $username not found") }
        return build(user)
    }
}