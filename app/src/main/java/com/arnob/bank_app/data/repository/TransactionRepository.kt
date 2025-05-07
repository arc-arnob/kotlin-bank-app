package com.arnob.bank_app.data.repository

import com.arnob.bank_app.data.db.dao.TransactionDao
import com.arnob.bank_app.data.model.Transaction

class TransactionRepository(private val dao: TransactionDao) {
    suspend fun logTransaction(tx: Transaction) = dao.insertTransaction(tx)

    suspend fun getTransactionsForUser(userId: Long): List<Transaction> = dao.getTransactionsForUser(userId)
}