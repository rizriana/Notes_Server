package com.secondlab.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        // path parameter - localhost:8080/notes/{id}
        get("/note/{id}") {
            val id = call.parameters["id"]
            call.respond("$id")
        }

        // query parameter - localhost:8080/note?id=value
        get("/note") {
            val id = call.request.queryParameters["id"]
            call.respond("$id")
        }

        route("/notes") {

            // localhost:8080/notes/create
            route("/create") {
                post {
                    val body = call.receive<String>()
                    call.respond(body)
                }
            }

            // localhost:8080/notes
            delete {
                val body = call.receive<String>()
                call.respond(body)
            }
        }
    }
}
