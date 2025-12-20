package com.example.shestore.ui.feature.auth

sealed class VerificationCodeEvent {
    data class CodeChanged(val value: String) : VerificationCodeEvent()
    data object Submit : VerificationCodeEvent()
    data object ResendCode : VerificationCodeEvent()
}