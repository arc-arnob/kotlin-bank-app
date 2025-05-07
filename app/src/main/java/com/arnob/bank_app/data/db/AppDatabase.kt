package com.arnob.bank_app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arnob.bank_app.data.db.dao.BalanceDao
import com.arnob.bank_app.data.db.dao.TransactionDao
import com.arnob.bank_app.data.db.dao.UserDao
import com.arnob.bank_app.data.model.Balance
import com.arnob.bank_app.data.model.Transaction
import com.arnob.bank_app.data.model.User

@Database(
    entities = [User::class, Balance::class, Transaction::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun balanceDao(): BalanceDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_app_database"
                )
                    .fallbackToDestructiveMigration() // If you're still in dev and okay with wiping data on schema change:
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}