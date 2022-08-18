package com.secondlab.repository

import com.secondlab.data.model.User
import com.secondlab.data.table.UserTable
import com.secondlab.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class Repository {
    suspend fun addUser(user: User) = dbQuery {
        UserTable.insert { userTable ->
            userTable[UserTable.email] = user.email
            userTable[UserTable.userName] = user.userName
            userTable[UserTable.hashPassword] = user.hashPassword
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
            userName = row[UserTable.userName],
            hashPassword = row[UserTable.hashPassword]
        )
    }
}