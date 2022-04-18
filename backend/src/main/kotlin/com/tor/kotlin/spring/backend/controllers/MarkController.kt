package com.tor.kotlin.spring.backend.controllers

import com.tor.kotlin.spring.backend.SwaggerConfig.Companion.BEARER_API_KEY
import com.tor.kotlin.spring.backend.jaas.model.SaveMarkDTO
import com.tor.kotlin.spring.backend.repo.MarkRESTRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * User: tor
 */
@RestController
@RequestMapping("/api/mark/rest/v199")
@Api(value = "/api/mark/rest/v199", description = "Rest API for Mark View", tags = arrayOf("Mark API"))
class MarkController {
    @Autowired
    lateinit var repo: MarkRESTRepository

    @ApiOperation(value = "get subject`s", authorizations = arrayOf( Authorization(value = BEARER_API_KEY)) )

    @GetMapping("/subjects")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    fun getSubjects(authentication: Authentication): ResponseEntity<List<Pair<Int, String>>> {
        return ResponseEntity.ok(repo.getSubjects(0))
    }

    @GetMapping("/subjects/{sid}/lessontypes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    fun getLessonTypes(authentication: Authentication, @PathVariable sid: Int): ResponseEntity<List<Pair<Int, String>>> {
        return ResponseEntity.ok(repo.getLessonTyps(0, sid))
    }

    @GetMapping("/subjects/{sid}/lessontypes/{ltid}/psgs", produces = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    fun getPSGs(authentication: Authentication, @PathVariable sid: Int, @PathVariable ltid: Int): ResponseEntity<List<Pair<Int, String>>> {
        return ResponseEntity.ok(repo.getPSGs(0, sid, ltid))
    }

    @GetMapping("/subjects/{sid}/lessontypes/{ltid}/psgs/{psgid}/handsontable", produces = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    fun getHandsontable(authentication: Authentication, @PathVariable sid: Int, @PathVariable ltid: Int, @PathVariable psgid: Int): ResponseEntity<String> {
        return ResponseEntity.ok(repo.getHandsontable(0, sid, ltid, psgid))
    }

    @PostMapping("/subjects/{sid}/lessontypes/{ltid}/psgs/{psgid}/handsontable", produces = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    fun setHandsontable(authentication: Authentication, @PathVariable sid: Int, @PathVariable ltid: Int, @PathVariable psgid: Int, @Valid @RequestBody dto: SaveMarkDTO): ResponseEntity<*> {
        return if (repo.setHandsontableMark(0, sid, ltid, psgid, dto)) {
            ResponseEntity.ok("ok")
        } else {
            ResponseEntity.badRequest().body("Error")
        }
    }
}