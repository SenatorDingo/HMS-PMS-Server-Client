package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import seg3102.group25.wellmeadows.hmspms.adapters.factories.StaffAccountDtoFactory
import seg3102.group25.wellmeadows.hmspms.adapters.repositories.StaffAccountRepoAdapter
import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterStaffDTO
import seg3102.group25.wellmeadows.hmspms.domain.staff.entities.account.StaffAccount
import java.util.*


@RestController
@RequestMapping("admin", produces = ["application/hal+json"])
class WebAdminController {

    //@Autowired
    //var staffAccountService = StaffAccountRepoAdapter()

    @GetMapping("getStaffAccount/{id}")
    fun getStaffAccountMethod(@PathVariable id: String): ResponseEntity<StaffAccount?>? {
        println("Get Staff Account")

        /*
        val staffAccount: StaffAccount? = staffAccountService.find(id)
        return if (staffAccount != null) {
            ResponseEntity<StaffAccount?>(staffAccount, HttpStatus.OK)
        } else {
            ResponseEntity<StaffAccount?>(null, HttpStatus.NOT_FOUND)
        }

        */
        return ResponseEntity<StaffAccount?>(null, HttpStatus.OK)

    }

    @PostMapping("createStaffAccount/")
    fun createStaffAccountMethod(@RequestBody staffAccountDTO: RegisterStaffDTO): ResponseEntity<StaffAccount?>? {
        println("Create Staff Account")
        /*
        val staffAccountFactory = StaffAccountDtoFactory()
        val staffAccount: StaffAccount = staffAccountFactory.createStaffAccount(staffAccountDTO)
        */
        return ResponseEntity<StaffAccount?>(null, HttpStatus.OK)

        //return staffAccountService.save(staffAccount)
    }

}