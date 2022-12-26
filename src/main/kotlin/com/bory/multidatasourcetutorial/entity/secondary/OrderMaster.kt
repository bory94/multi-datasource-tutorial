package com.bory.multidatasourcetutorial.entity.secondary

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "order_master")
@EntityListeners(AuditingEntityListener::class)
class OrderMaster(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @CreatedDate
    var createdTime: LocalDateTime? = null,
    @LastModifiedDate
    var updatedTime: LocalDateTime? = null
) {
}