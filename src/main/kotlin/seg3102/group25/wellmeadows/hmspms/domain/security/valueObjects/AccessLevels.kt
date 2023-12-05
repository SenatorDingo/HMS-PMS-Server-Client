package seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects

import seg3102.group25.wellmeadows.hmspms.domain.staff.valueObjects.StaffType
import java.util.*

enum class AccessLevels {
    AdmitPatient {
        override val staffType: List<StaffType> = listOf(StaffType.Nurse, StaffType.ChargeNurse)
    },
    DischargePatient {
        override val staffType: List<StaffType> = listOf(StaffType.Nurse, StaffType.ChargeNurse)
    },
    AdmitPatientRequestList {
        override val staffType: List<StaffType> = listOf(StaffType.PersonnelOfficer, StaffType.MedicalDirector)
    },
    PrescribeMedication {
        override val staffType: List<StaffType> = listOf(StaffType.LocalDoctor)
    },
    RegisterPatient {
        override val staffType: List<StaffType> = listOf(StaffType.Auxiliary, StaffType.Nurse)
    },
    RegisterStaff {
        override val staffType: List<StaffType> = listOf(StaffType.PersonnelOfficer)
    },
    RequestPatientAdmission {
        override val staffType: List<StaffType> = listOf(StaffType.MedicalDirector, StaffType.ChargeNurse)
    },
    UpdatePatientFile {
        override val staffType: List<StaffType> = listOf(StaffType.Nurse, StaffType.LocalDoctor)
    },
    VisualizeDivision {
        override val staffType: List<StaffType> = listOf(StaffType.PersonnelOfficer, StaffType.MedicalDirector)
    },
    ConsultPatientFile {
        override val staffType: List<StaffType> = listOf(StaffType.LocalDoctor, StaffType.MedicalDirector)
    };


    abstract val staffType: List<StaffType>
}