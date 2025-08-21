package com.ecommercial.shopping.userservice.global.config
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy

@Configuration
class DataSourceConfig(
    private val dataSourceProperties: DataSourceProperties
) {

    @Bean
    fun routingDataSource(): LazyConnectionDataSourceProxy {
        try {
            println("dsdsds@!@#@")
            val routingDataSource = RoutingDataSource()

            val write = dataSourceProperties.write
            val writeDataSource = createDataSource(write.url)

            val dataSourceMap = HashMap<Any, Any>()
            dataSourceMap[write.url] = writeDataSource

            dataSourceProperties.reads.forEach { read ->
                dataSourceMap[read.name] = createDataSource(read.url)
            }

            routingDataSource.setDefaultTargetDataSource(writeDataSource)
            routingDataSource.setTargetDataSources(dataSourceMap)
            routingDataSource.afterPropertiesSet()

            return LazyConnectionDataSourceProxy(routingDataSource)
        } catch (e: Exception) {
            println(e.message)
            println("@@@@@@@@@@@@@")
            throw e
        }

    }

    private fun createDataSource(url: String): HikariDataSource {
        val driverName : String = dataSourceProperties.driverClassName
        val userName: String = dataSourceProperties.username
        val password: String = dataSourceProperties.password
        return HikariDataSource().apply {
            driverClassName = driverName
            username = userName
            this.password = password
            jdbcUrl = url
        }
    }
}