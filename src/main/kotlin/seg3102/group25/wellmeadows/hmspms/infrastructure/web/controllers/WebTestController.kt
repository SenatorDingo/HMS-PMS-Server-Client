package seg3102.group25.wellmeadows.hmspms.infrastructure.web.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("test", produces = ["application/hal+json"])
class WebTestController {

    @GetMapping("getting/{str}")
    fun get(@PathVariable str: String): ResponseEntity<String?>? {
        println("Testing GET Method: $str")
        return ResponseEntity<String?>("Works", HttpStatus.OK)
    }

    @PostMapping("posting")
    fun post(@RequestBody str: String): ResponseEntity<String?>? {
        println("Testing POST Method: $str")
        return ResponseEntity<String?>("Works", HttpStatus.OK)
    }

}