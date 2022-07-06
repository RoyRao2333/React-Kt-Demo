package com.royrao.reactdemo.database

import com.zaxxer.hikari.HikariDataSource
import org.ktorm.database.Database

@Suppress("JoinDeclarationAndAssignment")
class DBManager private constructor() {
    private var dataSource: HikariDataSource
    private val db: Database

    private object Singleton {
        val shared = DBManager()
    }

    companion object {
        private var initialized = false

        fun shared() = Singleton.shared
    }

    // Singleton Protector
    init {
        if (!initialized) {
            initialized = true
        } else {
            throw Throwable("Singleton is being attacked.")
        }
    }

    init {
        dataSource = HikariCP.init(
            url = "jdbc:mysql://localhost:3765/ReactKtDemo",
            username = "root",
            password = "20000215",
        )
        db = Database.connect(dataSource)
    }
}
