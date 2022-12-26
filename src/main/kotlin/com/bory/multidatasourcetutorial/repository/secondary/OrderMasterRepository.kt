package com.bory.multidatasourcetutorial.repository.secondary

import com.bory.multidatasourcetutorial.entity.secondary.OrderMaster
import org.springframework.data.jpa.repository.JpaRepository

interface OrderMasterRepository : JpaRepository<OrderMaster, Long> {
}