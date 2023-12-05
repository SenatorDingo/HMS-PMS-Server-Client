package seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper

import com.google.firebase.database.PropertyName
import seg3102.group25.wellmeadows.hmspms.domain.constituent.valueObjects.ConstituentType

class DatabaseConstituentFile (
    @get:PropertyName("constituentID")
    @set:PropertyName("constituentID")
    var constituentID: String? = null,

    @get:PropertyName("firstName")
    @set:PropertyName("firstName")
    var firstName: String? = null,

    @get:PropertyName("lastName")
    @set:PropertyName("lastName")
    var lastName: String? = null,

    @get:PropertyName("address")
    @set:PropertyName("address")
    var address: String? = null,

    @get:PropertyName("telephoneNumber")
    @set:PropertyName("telephoneNumber")
    var telephoneNumber: String? = null,

    @get:PropertyName("relationship")
    @set:PropertyName("relationship")
    var relationship: String? = null,

    @get:PropertyName("constituentType")
    @set:PropertyName("constituentType")
    var constituentType: ConstituentType = ConstituentType.NextOfKin
)