package com.royrao.reactdemo.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

object HikariCP {
    private val pools: ConcurrentMap<String, HikariDataSource> = ConcurrentHashMap()

    fun init(name: String = "default", url: String, username: String, password: String): HikariDataSource {
        val config = HikariConfig()
        config.jdbcUrl = url
        config.username = username
        config.password = password
        config.driverClassName = "com.mysql.cj.jdbc.Driver"
        config.maximumPoolSize = 10
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

        val existing: HikariDataSource? = pools[name]
        if (existing != null && existing.isClosed) {
            existing.close()
        }

        val ds = HikariDataSource(config)
        pools[name] = ds

        return ds
    }

    fun custom(name: String = "default", custom: HikariDataSource) {
        val existing: HikariDataSource? = pools[name]
        if (existing != null && existing.isClosed) {
            existing.close()
        }
        pools[name] = custom
    }

    fun dataSource(name: String = "default"): HikariDataSource {
        val ds: HikariDataSource? = pools[name]
        if (ds != null && !ds.isClosed) {
            return ds
        } else {
            throw IllegalStateException("DataSource ($name) is absent.")
        }
    }
}
