package com.arnob.bank_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnob.bank_app.data.repository.UserRepository
import com.arnob.bank_app.util.PreferenceHelper
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userRepository: UserRepository,
    private val preferenceHelper: PreferenceHelper
): ViewModel() {
    private val _registrationState = MutableLiveData<RegistrationState>()
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState
    val registrationState: LiveData<RegistrationState> = _registrationState

    fun register(username: String, password: String, name: String){
        if (username.isBlank() || password.isBlank() || name.isBlank()) {
            _registrationState.value = RegistrationState.Error("Username and password and name cannot be empty")
            return
        }
        viewModelScope.launch {
//            _loginState.value = LoginState.Loading

            try {
                val result = userRepository.registerUser(username, password, name)
                result.fold(
                    onSuccess = { user_id ->
                        // Save session
                        preferenceHelper.setLoggedInUserId(user_id)
                        _registrationState.value = RegistrationState.Success
                    },
                    onFailure = { exception ->
                        _registrationState.value = RegistrationState.Error(exception.message ?: "Registration failed")
                    }
                )
            } catch (e: Exception) {
                _registrationState.value = RegistrationState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}

sealed class RegistrationState {
    object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}