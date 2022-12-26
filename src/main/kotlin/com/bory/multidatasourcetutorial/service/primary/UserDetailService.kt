package com.bory.multidatasourcetutorial.service.primary

import com.bory.multidatasourcetutorial.config.primary.PrimaryTransactional
import com.bory.multidatasourcetutorial.entity.primary.UserDetail
import com.bory.multidatasourcetutorial.repository.primary.UserDetailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation

@Service
@PrimaryTransactional
class UserDetailService(
    private val userDetailRepository: UserDetailRepository
) {
    @PrimaryTransactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun findAll(): List<UserDetail> = userDetailRepository.findAll()

    fun insert(userDetail: UserDetail) = userDetailRepository.save(userDetail)
}