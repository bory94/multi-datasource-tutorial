package com.bory.multidatasourcetutorial.config.primary

import org.springframework.core.annotation.AliasFor
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Transactional("primaryTransactionManager")
annotation class PrimaryTransactional(
    @get:AliasFor(annotation = Transactional::class) val propagation: Propagation = Propagation.REQUIRED,
    @get:AliasFor(annotation = Transactional::class) val isolation: Isolation = Isolation.DEFAULT,
    @get:AliasFor(annotation = Transactional::class) val timeout: Int = 600,
    @get:AliasFor(annotation = Transactional::class) val readOnly: Boolean = false,
    @get:AliasFor(annotation = Transactional::class) val rollbackFor: Array<KClass<out Exception>> = [],
    @get:AliasFor(annotation = Transactional::class) val rollbackForClassName: Array<String> = [],
    @get:AliasFor(annotation = Transactional::class) val noRollbackFor: Array<KClass<out Exception>> = [],
    @get:AliasFor(annotation = Transactional::class) val noRollbackForClassName: Array<String> = []
) {

}
