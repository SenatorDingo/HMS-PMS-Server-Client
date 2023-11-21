package seg3102.group25.wellmeadows.hmspms.infrastructure.jpa.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional
import seg3102.group25.wellmeadows.hmspms.infrastructure.security.credentials.Staff
import java.util.*

interface StaffJpaRepository: CrudRepository<Staff, Long> {
    fun findByUsername(username: String): Optional<Staff>

    fun existsByUsername(username: String): Boolean

    @Transactional
    fun deleteByUsername(userName: String)
}