package com.example.shestore.ui.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationCodeViewModel @Inject constructor(
    // TODO: inject use cases / repository for verify + resend
) : ViewModel() {

    private val _state = MutableStateFlow(VerificationCodeUiState())
    val state: StateFlow<VerificationCodeUiState> = _state

    fun onEvent(event: VerificationCodeEvent) {
        when (event) {
            is VerificationCodeEvent.CodeChanged -> {
                // Only digits, max 6
                val filtered = event.value.filter { it.isDigit() }.take(6)
                _state.value = _state.value.copy(code = filtered)
            }
            VerificationCodeEvent.Submit -> submit()
            VerificationCodeEvent.ResendCode -> resend()
        }
    }

    private fun submit() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isSubmitting = true, errorMessage = null)
            // TODO: call verify-code API / use case
            _state.value = _state.value.copy(isSubmitting = false)
        }
    }

    private fun resend() {
        viewModelScope.launch {
            // TODO: call resend-code API / use case
        }
    }
}
