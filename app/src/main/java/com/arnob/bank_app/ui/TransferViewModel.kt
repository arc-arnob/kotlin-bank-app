package com.arnob.bank_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnob.bank_app.data.repository.BalanceRepository
import com.arnob.bank_app.util.PreferenceHelper
import kotlinx.coroutines.launch

class TransferViewModel(
    private val balanceRepository: BalanceRepository,
    private val preferenceHelper: PreferenceHelper

): ViewModel(){
    val transferState = MutableLiveData<TransferState>()
    fun sendMoney(toUsername: String, amount: Double?) {
        if (amount == null || toUsername.isBlank()) {
            transferState.value = TransferState.Error("Invalid input")
            return
        }

        viewModelScope.launch {
            val fromUserId = preferenceHelper.getLoggedInUserId()
            val result = balanceRepository.transferMoney(fromUserId, toUsername, amount)
            result
                .onSuccess { transferState.postValue(TransferState.Success) }
                .onFailure { e -> transferState.postValue(TransferState.Error(e.message ?: "Failed")) }
        }
    }
}

sealed class TransferState {
    object Success : TransferState()
    data class Error(val message: String) : TransferState()
}