package com.example.shestore.ui.feature.auth

sealed class RegisterEvent {
    data class FullNameChanged(val value: String) : RegisterEvent()
    data class EmailChanged(val value: String) : RegisterEvent()
    data class PasswordChanged(val value: String) : RegisterEvent()
    data object TogglePasswordVisibility : RegisterEvent()
    data object Submit : RegisterEvent()
}