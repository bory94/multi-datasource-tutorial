package com.bory.multidatasourcetutorial.service.secondary

import com.bory.multidatasourcetutorial.config.secondary.SecondaryTransactional
import com.bory.multidatasourcetutorial.entity.secondary.OrderMaster
import com.bory.multidatasourcetutorial.repository.secondary.OrderMasterRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation

@Service
@SecondaryTransactional
class OrderMasterService(
    private val orderMasterRepository: OrderMasterRepository
) {
    @SecondaryTransactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun findAll(): List<OrderMaster> = orderMasterRepository.findAll()

    fun insert(orderMaster: OrderMaster) = orderMasterRepository.save(orderMaster)
}