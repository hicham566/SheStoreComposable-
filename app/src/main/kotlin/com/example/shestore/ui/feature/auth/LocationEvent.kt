package com.example.shestore.ui.feature.auth

sealed class LocationEvent {
    data class CountryChanged(val countryName: String) : LocationEvent()
    data object Submit : LocationEvent()
    data object Skip : LocationEvent()
    data object ChangeLocationClicked : LocationEvent()  // for opening map / picker later
}