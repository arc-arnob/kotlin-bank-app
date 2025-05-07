package com.arnob.bank_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnob.bank_app.data.model.Transaction
import com.arnob.bank_app.data.repository.TransactionRepository
import com.arnob.bank_app.util.PreferenceHelper
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val repo: TransactionRepository,
    private val prefs: PreferenceHelper
) : ViewModel() {

    private val _transactionList = MutableLiveData<List<Transaction>>()
    val transactionList: LiveData<List<Transaction>> = _transactionList

    fun loadTransactions() {
        viewModelScope.launch {
            val userId = prefs.getLoggedInUserId()
            val data = repo.getTransactionsForUser(userId)
            _transactionList.postValue(data)
        }
    }
}