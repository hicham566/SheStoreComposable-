package com.example.shestore.ui.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    // Later you can inject use cases / repositories here
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.FullNameChanged -> {
                _state.value = _state.value.copy(fullName = event.value)
            }
            is RegisterEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.value)
            }
            is RegisterEvent.PasswordChanged -> {
                _state.value = _state.value.copy(password = event.value)
            }
            RegisterEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !_state.value.isPasswordVisible
                )
            }
            RegisterEvent.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {
        viewModelScope.launch {
            // TODO: call your sign-up use case here
            _state.value = _state.value.copy(isSubmitting = true, errorMessage = null)

            // fake delay / work later if you want
            _state.value = _state.value.copy(isSubmitting = false)
        }
    }
}