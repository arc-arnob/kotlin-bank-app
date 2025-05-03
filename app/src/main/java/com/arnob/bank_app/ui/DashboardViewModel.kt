package com.arnob.bank_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnob.bank_app.data.repository.BalanceRepository
import com.arnob.bank_app.util.PreferenceHelper
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val balanceRepository: BalanceRepository,
    private val preferenceHelper: PreferenceHelper
): ViewModel() {

    private val _balanceState = MutableLiveData<BalanceState>()
    val balanceState: LiveData<BalanceState> = _balanceState


    fun loadBalance(){
        viewModelScope.launch {
            val userId = preferenceHelper.getLoggedInUserId()
            val result = balanceRepository.getBalance(userId)
            result
                .onSuccess { amount ->
                    _balanceState.postValue(BalanceState.Success(amount))
                }
                .onFailure { e ->
                    _balanceState.postValue(BalanceState.Error(e.message ?: "Failed to load balance"))
                }
        }
    }


    fun addBalance(amount: Double?){
        if(amount  == null){
            _balanceState.value = BalanceState.Error("Invalid Balance")
            return
        }
        viewModelScope.launch {
            try {
                // get userId from SharedPreference
                val userId = preferenceHelper.getLoggedInUserId()

                val result = balanceRepository.addMoney(userId, amount)
                result.onSuccess { newAmount ->
                    // Update state with new balance
                    _balanceState.value = BalanceState.Success(newAmount)
                }.onFailure { e ->
                    // Log or show error
                    _balanceState.value = BalanceState.Error(e.message ?: "Process failed" )
                }
            }catch (e: Exception){
                // Log error
                _balanceState.value = BalanceState.Error(e.message ?: "Process failed" )
            }
        }
    }
}

sealed class BalanceState {
    data class  Success(val amount: Double) : BalanceState()
    data class Error(val message: String) : BalanceState()
}