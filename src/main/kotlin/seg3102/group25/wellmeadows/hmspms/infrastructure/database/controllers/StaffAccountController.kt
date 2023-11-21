package seg3102.group25.wellmeadows.hmspms.infrastructure.database.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.services.StaffAccountService


@RestController
class StaffAccountController {
    @Autowired
    var staffAccountService: StaffAccountService? = null
    @GetMapping("/admin/getStaffAccountDetails")
    @Throws(InterruptedException::class, Exception::class)
    fun getPatient(@RequestParam name: String?): StaffAccount? {
        return staffAccountService?.getPatientDetails(name)
    }

    @PostMapping("/admin/createStaffAccount")
    @Throws(InterruptedException::class, Exception::class)
    fun createPatient(@RequestBody staffAccount: StaffAccount): String? {
        return staffAccountService?.saveStaffDetails(staffAccount)
    }

    @PutMapping("/admin/updateStaffAccount")
    @Throws(InterruptedException::class, Exception::class)
    fun updatePatient(@RequestBody staffAccount: StaffAccount): String? {
        return staffAccountService?.updateStaffDetails(staffAccount)
    }

    @DeleteMapping("/admin/deleteStaffAccount")
    fun deletePatient(@RequestParam name: String): String? {
        return staffAccountService?.deletePatient(name)
    }
}