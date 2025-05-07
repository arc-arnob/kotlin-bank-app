package com.arnob.bank_app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arnob.bank_app.data.model.Transaction

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE userId = :userId ORDER BY timestamp DESC")
    suspend fun getTransactionsForUser(userId: Long): List<Transaction>
}