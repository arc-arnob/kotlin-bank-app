package com.arnob.bank_app.data.repository

import com.arnob.bank_app.data.db.dao.BalanceDao
import com.arnob.bank_app.data.db.dao.TransactionDao
import com.arnob.bank_app.data.db.dao.UserDao
import com.arnob.bank_app.data.model.Balance
import com.arnob.bank_app.data.model.Transaction
import com.arnob.bank_app.data.model.TransactionType

class BalanceRepository(private val balanceDao: BalanceDao, private  val userDao: UserDao, private  val transactionDao: TransactionDao) {
    // TODO: Change this to be extendable, UPI, Bank, Card etc etc..
    suspend fun addMoney(userId: Long, amount: Double): Result<Double>{
        return try {
            val existingUserBalance = balanceDao.getBalanceForUser(userId)
            if(existingUserBalance != null){
                val newAmount = existingUserBalance.amount + amount
                val updatedBalance = existingUserBalance.copy(amount = newAmount)
                balanceDao.updateBalance(updatedBalance)
                // TODO: DRY BROKEN
                val tx = Transaction(userId = userId, type = TransactionType.ADD, amount = amount, targetUser = null)
                transactionDao.insertTransaction(tx)
                Result.success(newAmount)
            }else{
                val newBalance = Balance(userId = userId, amount = amount)
                balanceDao.insertBalance(newBalance)
                // TODO: DRY BROKEN
                val tx = Transaction(userId = userId, type = TransactionType.ADD, amount = amount, targetUser = null)
                transactionDao.insertTransaction(tx)
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

    suspend fun transferMoney(fromUserId: Long, toUsername: String, amount: Double): Result<Unit> {
        return try {
            if (amount <= 0.0) return Result.failure(Exception("Invalid amount"))

            val senderBalance = balanceDao.getBalanceForUser(fromUserId) ?: return Result.failure(Exception("Sender balance not found"))
            if (senderBalance.amount < amount) return Result.failure(Exception("Insufficient funds"))

            val receiver = userDao.getUserByUsername(toUsername) ?: return Result.failure(Exception("Receiver not found"))
            val receiverBalance = balanceDao.getBalanceForUser(receiver.id)

            // Update balances
            balanceDao.updateBalance(senderBalance.copy(amount = senderBalance.amount - amount))
            if (receiverBalance == null) {
                balanceDao.insertBalance(Balance(userId = receiver.id, amount = amount))
            } else {
                balanceDao.updateBalance(receiverBalance.copy(amount = receiverBalance.amount + amount))
            }

            // LOG TRANSACTION // TODO: Add Failure Handling.
            // Sender Log
            val txSender = Transaction(userId = fromUserId, type = TransactionType.TRANSFER, amount = amount, targetUser = receiver.id.toString())
            transactionDao.insertTransaction(txSender)
            // Receiver Log
            val txReceiver = Transaction(userId = receiver.id, type = TransactionType.ADD, amount = amount, targetUser = fromUserId.toString())
            transactionDao.insertTransaction(txReceiver)

            Result.success(Unit) // Unit Corresponds to Void in Java.
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



}