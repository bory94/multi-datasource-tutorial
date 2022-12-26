package com.bory.multidatasourcetutorial.entity.primary

import javax.persistence.*

@Entity
@Table(name = "user_detail")
class UserDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null
) {

}