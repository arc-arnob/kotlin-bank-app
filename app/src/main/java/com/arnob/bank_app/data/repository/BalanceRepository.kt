package com.arnob.bank_app.data.repository

import com.arnob.bank_app.data.db.dao.BalanceDao
import com.arnob.bank_app.data.model.Balance

class BalanceRepository(private val balanceDao: BalanceDao) {
    // TODO: Change this to be extendable, UPI, Bank, Card etc etc..
    suspend fun addMoney(userId: Long, amount: Double): Result<Double>{
        return try {
            val existingUserBalance = balanceDao.getBalanceForUser(userId)
            if(existingUserBalance != null){
                val newAmount = existingUserBalance.amount + amount
                val updatedBalance = existingUserBalance.copy(amount = newAmount)
                balanceDao.updateBalance(updatedBalance)
                Result.success(newAmount)
            }else{
                val newBalance = Balance(userId = userId, amount = amount)
                balanceDao.insertBalance(newBalance)
                Result.success(amount)
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun getBalance(userId: Long): Result<Double> {
        return try {
            val balance = balanceDao.getBalanceForUser(userId)
            Result.success(balance?.amount ?: 0.0)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}