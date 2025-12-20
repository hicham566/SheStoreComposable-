package com.example.shestore.ui.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneNumberViewModel @Inject constructor(
    // later: inject use case / repo
) : ViewModel() {

    private val _state = MutableStateFlow(PhoneNumberUiState())
    val state: StateFlow<PhoneNumberUiState> = _state

    fun onEvent(event: PhoneNumberEvent) {
        when (event) {
            is PhoneNumberEvent.PhoneChanged -> {
                _state.value = _state.value.copy(phone = event.value)
            }
            PhoneNumberEvent.Submit -> submit()
        }
    }

    private fun submit() {
        viewModelScope.launch {
            // TODO: call send-code use case here
            _state.value = _state.value.copy(isSubmitting = true, errorMessage = null)

            // fake work for now
            _state.value = _state.value.copy(isSubmitting = false)
        }
    }
}
