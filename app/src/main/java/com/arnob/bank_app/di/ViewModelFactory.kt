package com.arnob.bank_app.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arnob.bank_app.data.db.AppDatabase
import com.arnob.bank_app.data.repository.UserRepository
import com.arnob.bank_app.ui.LoginViewModel
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

//        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
//            val database = AppDatabase.getDatabase(context)
//            val repository = UserRepository(database.userDao())
//            return RegisterViewModel(repository) as T
//        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}