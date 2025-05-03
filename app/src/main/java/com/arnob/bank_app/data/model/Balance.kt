package com.arnob.bank_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balance")
data class Balance(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,          // foreign key reference
    val amount: Double = 0.0   // initial balance
)