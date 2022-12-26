package com.bory.multidatasourcetutorial.controller.secondary

import com.bory.multidatasourcetutorial.entity.secondary.OrderMaster
import com.bory.multidatasourcetutorial.service.secondary.OrderMasterService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderMasterController(
    private val orderMasterService: OrderMasterService
) {
    @GetMapping
    fun findAll(): List<OrderMaster> = orderMasterService.findAll()

    @PostMapping
    fun insert(@RequestBody orderMaster: OrderMaster) = orderMasterService.insert(orderMaster)
}