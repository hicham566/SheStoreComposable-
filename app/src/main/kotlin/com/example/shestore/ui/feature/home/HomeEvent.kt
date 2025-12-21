package com.example.shestore.ui.feature.home

sealed class HomeEvent {
    data object Refresh : HomeEvent()
    data class OnProductClick(val productId: String) : HomeEvent()
    data object OnSeeAllNewArrivals : HomeEvent()
}