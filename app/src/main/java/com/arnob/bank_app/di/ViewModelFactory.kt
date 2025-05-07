package com.arnob.bank_app.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arnob.bank_app.data.db.AppDatabase
import com.arnob.bank_app.data.repository.BalanceRepository
import com.arnob.bank_app.data.repository.TransactionRepository
import com.arnob.bank_app.data.repository.UserRepository
import com.arnob.bank_app.ui.*
import com.arnob.bank_app.util.PreferenceHelper

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val repository = UserRepository(database.userDao())
            val preferenceHelper = PreferenceHelper(context)
            return LoginViewModel(repository, preferenceHelper) as T
        }

        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val repository = UserRepository(database.userDao())
            val preferenceHelper = PreferenceHelper(context)
            return RegisterViewModel(repository, preferenceHelper) as T
        }

        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val repository = BalanceRepository(database.balanceDao(), database.userDao(), database.transactionDao())
            val preferenceHelper = PreferenceHelper(context)
            return DashboardViewModel(repository, preferenceHelper) as T
        }
        if (modelClass.isAssignableFrom(TransferViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val repository = BalanceRepository(database.balanceDao(), database.userDao(), database.transactionDao())
            val preferenceHelper = PreferenceHelper(context)
            return TransferViewModel(repository, preferenceHelper) as T
        }
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val repository = TransactionRepository(database.transactionDao())
            val preferenceHelper = PreferenceHelper(context)
            return TransactionViewModel(repository, preferenceHelper) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}