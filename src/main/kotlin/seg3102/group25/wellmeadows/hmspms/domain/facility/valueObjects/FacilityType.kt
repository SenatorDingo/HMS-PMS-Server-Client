package seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects

enum class FacilityType {
    Ward{
        override fun setDivisionName(facilityDivisionName: String) {
            this.facilityDivisionName = facilityDivisionName
        }

        override fun setDivisionID(facilityDivisionID: String) {
            this.facilityDivisionID = facilityDivisionID
        }
    },
    Clinic{
        override fun setDivisionName(facilityDivisionName: String) {
            this.facilityDivisionName = facilityDivisionName
        }

        override fun setDivisionID(facilityDivisionID: String) {
            this.facilityDivisionID = facilityDivisionID
        }
    },
    None {
        override fun setDivisionName(facilityDivisionName: String) {
            this.facilityDivisionName = facilityDivisionName
        }

        override fun setDivisionID(facilityDivisionID: String) {
            this.facilityDivisionID = facilityDivisionID
        }
    };

    var facilityDivisionName: String = ""
    var facilityDivisionID: String = ""

    abstract fun setDivisionName(facilityDivisionName: String)
    abstract fun setDivisionID(facilityDivisionID: String)
}