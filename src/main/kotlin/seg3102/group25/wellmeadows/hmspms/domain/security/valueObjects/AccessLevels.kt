package seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects

import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType
import java.util.*

enum class AccessLevels{
    AdmitPatient{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    DischargePatient{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    AdmitPatientRequestList{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    PrescribeMedication{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    RegisterPatient{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    RegisterStaff{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    RequestPatientAdmission{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    UpdatePatientFile{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    VisualizeDivision{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    },
    ConsultPatientFile{
        override val staffType: List<StaffType> = listOf() // TODO: Add StaffTypes
    };

    abstract val staffType: List<StaffType>
}