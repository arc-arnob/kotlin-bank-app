package com.arnob.bank_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnob.bank_app.data.repository.UserRepository
import com.arnob.bank_app.util.PreferenceHelper
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
    private val preferenceHelper: PreferenceHelper
) : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(username: String, password: String){
        if (username.isBlank() || password.isBlank()) {
            _loginState.value = LoginState.Error("Username and password cannot be empty")
            return
        }
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val result = userRepository.loginUser(username, password)
                result.fold(
                    onSuccess = { user ->
                        // Save session
                        preferenceHelper.setLoggedInUserId(user.id)
                        _loginState.value = LoginState.Success
                    },
                    onFailure = { exception ->
                        _loginState.value = LoginState.Error(exception.message ?: "Login failed")
                    }
                )
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}

sealed class LoginState {
    object Initial : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}