package com.ecommercial.shopping.orderservice.global.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "spring.datasource.replication")
class DataSourceProperties(
    var username: String = "",
    var password: String = "",
    var driverClassName: String = "",
    var write: Write = Write(),
    var reads: List<Read> = emptyList()
) {
    class Write {
        var name: String = ""
        var url: String = ""
    }

    class Read {
        var name: String = ""
        var url: String = ""
    }
}
