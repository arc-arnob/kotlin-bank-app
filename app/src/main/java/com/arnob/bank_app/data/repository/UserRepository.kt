package com.arnob.bank_app.data.repository

import com.arnob.bank_app.data.db.dao.UserDao
import com.arnob.bank_app.data.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(username: String, password: String, name: String): Result<Long> {
        return try {
            val existingUser = userDao.getUserByUsername(username)
            if (existingUser != null) {
                Result.failure(Exception("Username already taken"))
            } else {
                val user = User(username = username, password = password, name = name)
                val userId = userDao.insertUser(user)
                Result.success(userId)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(username: String, password: String): Result<User> {
        return try {
            val user = userDao.login(username, password)
            println(user?.id)
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Invalid username or password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getOtherUsers(currentUserId: Long): Result<List<User>> {
        return try {
            val users = userDao.getOtherUsers(currentUserId)
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}