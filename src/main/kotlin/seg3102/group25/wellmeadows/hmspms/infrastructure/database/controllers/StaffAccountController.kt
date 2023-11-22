package seg3102.group25.wellmeadows.hmspms.infrastructure.database.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import seg3102.group25.wellmeadows.hmspms.adapters.repositories.StaffAccountRepoAdapter
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount

@RestController
class StaffAccountController {

    @Autowired
    var staffAccountService = StaffAccountRepoAdapter()

    @GetMapping("/admin/getStaffAccountDetails/")
    @Throws(InterruptedException::class, Exception::class)
    fun getStaffAccount(@RequestParam id: String): StaffAccount? {
        return staffAccountService.find(id)
    }

    @PostMapping("/admin/createStaffAccount/")
    @Throws(InterruptedException::class, Exception::class)
    fun createStaffAccount(@RequestBody staffAccount: StaffAccount): StaffAccount {
        return staffAccountService.save(staffAccount)
    }

    /* Not Yet Implemented
    @PutMapping("/admin/updateStaffAccount")
    @Throws(InterruptedException::class, Exception::class)
    fun updateStaffAccount(@RequestBody staffAccount: StaffAccount): String? {
        return staffAccountService?.updateStaffDetails(staffAccount)
    }

    @DeleteMapping("/admin/deleteStaffAccount")
    fun deleteStaffAccount(@RequestParam name: String): String? {
        return staffAccountService?.deletePatient(name)
    }
    */
}