package com.royrao.reactdemo.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private fun Route.homePageRouting() {
    get("/home") {
        call.respondText { "Hello, World!!!!" }
    }
}

fun Application.registerHomePageRouting() {
    routing {
        homePageRouting()
    }
}
