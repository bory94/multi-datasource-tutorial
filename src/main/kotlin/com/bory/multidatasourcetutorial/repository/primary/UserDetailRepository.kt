package com.bory.multidatasourcetutorial.repository.primary

import com.bory.multidatasourcetutorial.entity.primary.UserDetail
import org.springframework.data.jpa.repository.JpaRepository

interface UserDetailRepository : JpaRepository<UserDetail, Long> {
}