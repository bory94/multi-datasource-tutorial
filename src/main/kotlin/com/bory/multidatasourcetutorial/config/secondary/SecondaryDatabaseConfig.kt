package com.bory.multidatasourcetutorial.config.secondary

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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "secondaryEntityManagerFactory",
    transactionManagerRef = "secondaryTransactionManager",
    basePackages = ["com.bory.multidatasourcetutorial.repository.secondary"]
)
class SecondaryDatabaseConfig(
    @Value("\${spring.secondary.datasource.url}") private val url: String,
    @Value("\${spring.secondary.datasource.username}") private val username: String,
    @Value("\${spring.secondary.datasource.password}") private val password: String,
) {

    @Bean
    fun secondaryDbDataSource(): DataSource = DataSourceBuilder.create()
        .url(url)
        .username(username)
        .password(password)
        .build()

    @Bean
    @ConfigurationProperties("spring.secondary.jpa")
    fun secondaryJpaProperties() = JpaProperties()

    @Bean
    @ConfigurationProperties("spring.secondary.jpa.hibernate")
    fun secondaryHibernateProperties() = HibernateProperties()

    @Bean
    fun secondaryEntityManagerFactory(jpaVendorAdapter: JpaVendorAdapter): LocalContainerEntityManagerFactoryBean =
        EntityManagerFactoryBuilder(jpaVendorAdapter, secondaryJpaProperties().properties, null)
            .dataSource(secondaryDbDataSource())
            .properties(
                secondaryHibernateProperties().determineHibernateProperties(
                    secondaryJpaProperties().properties, HibernateSettings()
                )
            )
            .persistenceUnit("secondary_db")
            .packages("com.bory.multidatasourcetutorial.entity.secondary")
            .build()

    @Bean
    fun secondaryTransactionManager(
        @Qualifier("secondaryEntityManagerFactory") secondaryEntityManagerFactory: EntityManagerFactory
    ) = JpaTransactionManager(secondaryEntityManagerFactory)
}