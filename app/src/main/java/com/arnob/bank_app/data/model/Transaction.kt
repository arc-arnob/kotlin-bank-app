package com.arnob.bank_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val type: TransactionType, // Enum: ADD, WITHDRAW, TRANSFER
    val amount: Double,
    val targetUser: String?, // For transfer, otherwise null
    val timestamp: Long = System.currentTimeMillis()
)

enum class TransactionType {
    ADD, WITHDRAW, TRANSFER
}