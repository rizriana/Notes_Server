package com.secondlab.routes

import com.secondlab.data.model.Note
import com.secondlab.data.model.SimpleResponse
import com.secondlab.data.model.User
import com.secondlab.repository.Repository
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

const val GET_NOTES = "$API_VERSION/notes"
const val CREATE_NOTES = "$GET_NOTES/create"
const val UPDATE_NOTES = "$GET_NOTES/update"
const val DELETE_NOTES = "$GET_NOTES/delete"

fun Route.noteRoutes(
    db: Repository,
    hashFunction: (String) -> String
) {
    authenticate("jwt") {
        get(GET_NOTES) {
            try {
                val email = call.principal<User>()!!.email
                val notes = db.getAllNotes(email)
                call.respond(HttpStatusCode.OK, notes)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, emptyList<Note>())
            }
        }

        post(CREATE_NOTES) {
            val note = try {
                call.receive<Note>()
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "Missing Fields"))
                return@post
            }

            try {
                val email = call.principal<User>()!!.email
                db.addNote(note, email)
                call.respond(HttpStatusCode.OK, SimpleResponse(true, "Note Added Successfully!"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, SimpleResponse(false, e.message ?: "Some Problem Occurred!"))
            }
        }

        post(UPDATE_NOTES) {
            val note = try {
                call.receive<Note>()
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "Missing Fields"))
                return@post
            }

            try {
                val email = call.principal<User>()!!.email
                db.updateNote(note, email)
                call.respond(HttpStatusCode.OK, SimpleResponse(true, "Note Updated Successfully!"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.OK, SimpleResponse(true, e.message ?: "Some Problem Occurred!"))
            }
        }

        delete(DELETE_NOTES) {
            val noteId = try {
                call.request.queryParameters["id"]!!
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "Query Parameter: id is note present"))
                return@delete
            }

            try {
                val email = call.principal<User>()!!.email
                db.deleteNote(noteId, email)
                call.respond(HttpStatusCode.OK, SimpleResponse(true, "Note Deleted Successfully!"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, SimpleResponse(false, e.message ?: "Some Problem Occurred!"))
            }
        }
    }
}