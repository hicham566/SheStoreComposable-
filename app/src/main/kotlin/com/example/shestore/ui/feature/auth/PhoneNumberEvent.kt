package com.example.shestore.ui.feature.auth

sealed class PhoneNumberEvent {
    data class PhoneChanged(val value: String) : PhoneNumberEvent()
    data object Submit : PhoneNumberEvent()
}