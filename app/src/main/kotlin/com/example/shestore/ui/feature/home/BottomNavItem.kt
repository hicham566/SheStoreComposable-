package com.example.shestore.ui.feature.home

import androidx.annotation.DrawableRes
import com.example.shestore.R


sealed class BottomNavItem(
    val route: String,
    @DrawableRes val iconRes: Int,
    val label: String
) {
    data object Shop : BottomNavItem("home", R.drawable.ic_nav_shop, "Shop")
    data object Categories : BottomNavItem("categories", R.drawable.ic_nav_categories, "Categories")
    data object Bag : BottomNavItem("bag", R.drawable.ic_nav_cart, "Bag")
    data object Saved : BottomNavItem("saved", R.drawable.ic_nav_bookmark, "Saved")
    data object Profile : BottomNavItem("profile", R.drawable.ic_nav_profile, "Profile")
}

val bottomNavItems = listOf(
    BottomNavItem.Shop,
    BottomNavItem.Categories,
    BottomNavItem.Bag,
    BottomNavItem.Saved,
    BottomNavItem.Profile
)
