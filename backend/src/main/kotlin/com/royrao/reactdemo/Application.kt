package com.royrao.reactdemo

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.royrao.reactdemo.routes.registerHomePageRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import java.text.SimpleDateFormat

private fun Application.registerRoutings() {
    registerHomePageRouting()
}

fun main() {
    embeddedServer(Netty, port = 9098, host = "127.0.0.1") {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
                dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                setSerializationInclusion(JsonInclude.Include.NON_NULL)
            }
        }

        registerRoutings()
    }.start(wait = true)
}
