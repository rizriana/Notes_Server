package com.secondlab.routes

import com.secondlab.authentication.JwtService
import com.secondlab.data.model.LoginRequest
import com.secondlab.data.model.RegisterRequest
import com.secondlab.data.model.SimpleResponse
import com.secondlab.data.model.User
import com.secondlab.repository.Repository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

const val API_VERSION = "/v1"
const val USERS = "$API_VERSION/users"
const val REGISTER_REQUEST = "$USERS/register"
const val LOGIN_REQUEST = "$USERS/login"

fun Route.userRoutes(
    db: Repository,
    jwtService: JwtService,
    hashFunction: (String) -> String
) {
    post(REGISTER_REQUEST) {
        val registerRequest = try {
            call.receive<RegisterRequest>()
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "Missing Some Fields"))
            return@post
        }

        try {
            val user = User(registerRequest.email, hashFunction(registerRequest.password), registerRequest.name)
            db.addUser(user)
            call.respond(HttpStatusCode.OK, SimpleResponse(true, jwtService.generateToken(user)))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict, SimpleResponse(false, e.message ?: "Some Problem Occurred!"))
        }
    }

    post(LOGIN_REQUEST) {
        val loginRequest = try {
            call.receive<LoginRequest>()
        } catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,SimpleResponse(false,"Missing Some Fields"))
            return@post
        }

        try {
            val user = db.findUserByEmail(loginRequest.email)
            if(user == null){
                call.respond(HttpStatusCode.BadRequest,SimpleResponse(false,"Wrong Email Id"))
            } else {

                if(user.hashPassword == hashFunction(loginRequest.password)){
                    call.respond(HttpStatusCode.OK,SimpleResponse(true,jwtService.generateToken(user)))
                } else{
                    call.respond(HttpStatusCode.BadRequest,SimpleResponse(false,"Password Incorrect!"))
                }
            }
        } catch (e:Exception){
            call.respond(HttpStatusCode.Conflict,SimpleResponse(false,e.message ?: "Some Problem Occurred!"))
        }
    }
}