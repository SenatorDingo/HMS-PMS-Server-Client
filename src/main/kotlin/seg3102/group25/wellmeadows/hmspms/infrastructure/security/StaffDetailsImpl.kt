package seg3102.group25.wellmeadows.hmspms.infrastructure.security

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.authority.SimpleGrantedAuthority
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount

class StaffDetailsImpl(val id: String,
                      @field:JsonIgnore private val password: String,
                      private val enabled: Boolean,
                      private val authorities: Collection<GrantedAuthority>) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return id
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}

fun build(staffAccount: StaffAccount): StaffDetailsImpl {
    val authorities = ArrayList<GrantedAuthority>()
    staffAccount.getTypes().forEach{role ->
        authorities.add(SimpleGrantedAuthority(role.name))
    }

    return StaffDetailsImpl(
        staffAccount.employeeNumber,
        staffAccount.getPassword(),
        staffAccount.isActive(),
        authorities)
}