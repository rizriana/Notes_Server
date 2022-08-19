package com.secondlab.repository

import com.secondlab.data.model.Note
import com.secondlab.data.model.User
import com.secondlab.data.table.NoteTable
import com.secondlab.data.table.UserTable
import com.secondlab.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*

class Repository {
    suspend fun addUser(user: User) {
        dbQuery {
            UserTable.insert { ut ->
                ut[UserTable.email] = user.email
                ut[UserTable.hashPassword] = user.hashPassword
                ut[UserTable.name] = user.userName
            }
        }
    }

    suspend fun findUserByEmail(email: String) = dbQuery {
        UserTable.select { UserTable.email.eq(email) }
            .map { rowToUser(it) }
            .singleOrNull()
    }

    private fun rowToUser(row: ResultRow?): User? {
        if (row == null) {
            return null
        }

        return User(
            email = row[UserTable.email],
            hashPassword = row[UserTable.hashPassword],
            userName = row[UserTable.name]
        )
    }

    /*======== NOTES ========*/
    suspend fun addNote(note: Note, email: String) {
        dbQuery {
            NoteTable.insert { noteTable ->
                noteTable[id] = note.id
                noteTable[userEmail] = email
                noteTable[noteTitle] = note.noteTitle
                noteTable[description] = note.description
                noteTable[date] = note.date
            }
        }
    }

    suspend fun getAllNotes(email: String): List<Note> = dbQuery {
        NoteTable.select {
            NoteTable.userEmail.eq(email)
        }.mapNotNull { rowToNote(it) }
    }

    suspend fun updateNote(note: Note, email: String) {
        dbQuery {
            NoteTable.update(
                where = {
                    NoteTable.userEmail.eq(email) and NoteTable.id.eq(note.id)
                }
            ) { noteTable ->
                noteTable[noteTitle] = note.noteTitle
                noteTable[description] = note.description
                noteTable[date] = note.date
            }
        }
    }

    suspend fun deleteNote(id: String) {
        dbQuery {
            NoteTable.deleteWhere {
                NoteTable.id.eq(id)
            }
        }
    }

    private fun rowToNote(row: ResultRow?): Note? {
        if (row == null) {
            return null
        }

        return Note(
            id = row[NoteTable.id],
            noteTitle = row[NoteTable.noteTitle],
            description = row[NoteTable.description],
            date = row[NoteTable.date]
        )
    }
}