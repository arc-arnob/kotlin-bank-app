package com.arnob.bank_app.data.db.dao

import androidx.room.*
import com.arnob.bank_app.data.model.Balance

@Dao
interface BalanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balance: Balance): Long

    @Query("SELECT * FROM balance WHERE userId = :userId LIMIT 1")
    suspend fun getBalanceForUser(userId: Long): Balance?

    @Update
    suspend fun updateBalance(balance: Balance)

    @Delete
    suspend fun deleteBalance(balance: Balance)
}