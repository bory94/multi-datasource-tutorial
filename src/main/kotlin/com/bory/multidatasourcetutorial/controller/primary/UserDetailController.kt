package com.bory.multidatasourcetutorial.controller.primary

import com.bory.multidatasourcetutorial.entity.primary.UserDetail
import com.bory.multidatasourcetutorial.service.primary.UserDetailService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserDetailController(
    private val userDetailService: UserDetailService
) {
    @GetMapping
    fun findAll(): List<UserDetail> = userDetailService.findAll()

    @PostMapping
    fun insert(@RequestBody userDetail: UserDetail) = userDetailService.insert(userDetail)
}