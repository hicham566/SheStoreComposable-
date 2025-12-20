package com.example.shestore.ui.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    // later: inject location / user repository if you want
) : ViewModel() {

    private val _state = MutableStateFlow(LocationUiState())
    val state: StateFlow<LocationUiState> = _state

    fun onEvent(event: LocationEvent) {
        when (event) {
            is LocationEvent.CountryChanged -> {
                _state.value = _state.value.copy(countryName = event.countryName)
            }
            LocationEvent.Submit -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(isSubmitting = true, errorMessage = null)
                    // TODO: save location to backend / datastore
                    _state.value = _state.value.copy(isSubmitting = false)
                }
            }
            LocationEvent.Skip -> {
                // Navigation is handled in NavGraph; nothing to change in state here
            }
            LocationEvent.ChangeLocationClicked -> {
                // Hook for later: open map or country picker
            }
        }
    }
}
