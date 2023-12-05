package seg3102.group25.wellmeadows.hmspms.application.usecases.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.StaffShiftDTO
import seg3102.group25.wellmeadows.hmspms.application.usecases.RegisterStaff
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.entities.security.Security
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.domain.staff.facade.StaffFacade

class RegisterStaffImpl(
    private var securityFacade: SecurityFacade,
    private var staffFacade: StaffFacade,
    private var facilityFacade: FacilityFacade
): RegisterStaff {

    val security: Security = Security(AccessLevels.RegisterStaff)
    override fun registerStaff(staffNumber: String, registerStaffInfo: RegisterStaffDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security)){
            return staffFacade.createStaffAccount(registerStaffInfo)
        }
        return false
    }

    override fun updateShift(staffNumber: String, updateStaffShift: StaffShiftDTO): Boolean {
        if(securityFacade.checkAccess(staffNumber, security)){
            return facilityFacade.addShift(updateStaffShift)
        }
        return false
    }
}