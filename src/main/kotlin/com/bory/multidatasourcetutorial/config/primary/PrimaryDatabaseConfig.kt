package com.bory.multidatasourcetutorial.config.primary

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager",
    basePackages = ["com.bory.multidatasourcetutorial.repository.primary"]
)
class PrimaryDatabaseConfig(
    @Value("\${spring.primary.datasource.url}") private val url: String,
    @Value("\${spring.primary.datasource.username}") private val username: String,
    @Value("\${spring.primary.datasource.password}") private val password: String,
) {

    @Primary
    @Bean
    fun primaryDbDataSource(): DataSource = DataSourceBuilder.create()
        .url(url)
        .username(username)
        .password(password)
        .build()

    @Primary
    @Bean
    @ConfigurationProperties("spring.primary.jpa")
    fun primaryJpaProperties() = JpaProperties()

    @Primary
    @Bean
    @ConfigurationProperties("spring.primary.jpa.hibernate")
    fun primaryHibernateProperties() = HibernateProperties()

    @Primary
    @Bean
    fun primaryEntityManagerFactory(jpaVendorAdapter: JpaVendorAdapter): LocalContainerEntityManagerFactoryBean =
        EntityManagerFactoryBuilder(jpaVendorAdapter, primaryJpaProperties().properties, null)
            .dataSource(primaryDbDataSource())
            .properties(
                primaryHibernateProperties().determineHibernateProperties(
                    primaryJpaProperties().properties, HibernateSettings()
                )
            )
            .persistenceUnit("primary_db")
            .packages("com.bory.multidatasourcetutorial.entity.primary")
            .build()

    @Primary
    @Bean
    fun primaryTransactionManager(
        @Qualifier("primaryEntityManagerFactory") primaryEntityManagerFactory: EntityManagerFactory
    ) = JpaTransactionManager(primaryEntityManagerFactory)
}