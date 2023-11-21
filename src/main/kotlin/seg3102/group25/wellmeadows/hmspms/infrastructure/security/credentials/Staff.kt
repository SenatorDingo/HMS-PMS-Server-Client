package seg3102.group25.wellmeadows.hmspms.infrastructure.security.credentials

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType


@Entity
@Table(name = "staffs", uniqueConstraints = [UniqueConstraint(columnNames = ["username"])])
class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @NotBlank
    @Size(max = 20)
    var username: String = ""

    @NotBlank
    @Size(max = 120)
    var password: String = ""

    var enabled: Boolean = false


    @Enumerated(EnumType.STRING)
    var role: StaffType = StaffType.Nurse

    constructor() {}
    constructor(username: String, password: String, enabled: Boolean) {
        this.username = username
        this.password = password
        this.enabled = enabled
    }
}