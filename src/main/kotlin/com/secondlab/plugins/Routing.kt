package com.secondlab.plugins

import com.secondlab.authentication.JwtService
import com.secondlab.authentication.hash
import com.secondlab.data.model.User
import com.secondlab.repository.Repository
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {

    val db = Repository()
    val jwtService = JwtService()
    val hashFunction = { s:String -> hash(s) }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        // path parameter - localhost:8080/notes/{id}
        get("/note/{id}") {
            val id = call.parameters["id"]
            call.respond("$id")
        }

        get("/token"){
            val email = call.request.queryParameters["email"]!!
            val password = call.request.queryParameters["password"]!!
            val username = call.request.queryParameters["username"]!!

            val user = User(email,hashFunction(password),username)
            call.respond(jwtService.generateToken(user))
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
