package com.arnob.bank_app.data.db.dao

import androidx.room.*
import com.arnob.bank_app.data.model.User


@Dao
interface UserDao {
@Insert
suspend fun insertUser(user: User): Long // Suspend == async

@Query("SELECT * FROM users WHERE username = :username LIMIT 1")
suspend fun getUserByUsername(username: String): User?

@Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
suspend fun login(username: String, password: String): User?

@Query("SELECT * FROM users WHERE id != :currentUserId")
suspend fun getOtherUsers(currentUserId: Long): List<User>
}
