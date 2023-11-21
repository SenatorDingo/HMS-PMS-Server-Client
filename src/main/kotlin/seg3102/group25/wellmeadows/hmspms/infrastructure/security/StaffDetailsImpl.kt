package seg3102.group25.wellmeadows.hmspms.infrastructure.security

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.authority.SimpleGrantedAuthority
import seg3102.group25.wellmeadows.hmspms.infrastructure.security.credentials.Staff

class StaffDetailsImpl(val id: Long, private val username: String,
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
        return username
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

fun build(staff: Staff): StaffDetailsImpl {
    val authorities = ArrayList<GrantedAuthority>()
    authorities.add(SimpleGrantedAuthority(staff.role.name))
    return StaffDetailsImpl(
        staff.id,
        staff.username,
        staff.password,
        staff.enabled,
        authorities)
}