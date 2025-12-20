package com.example.shestore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shestore.ui.feature.auth.*
import com.example.shestore.ui.feature.home.HomeScreen
import com.example.shestore.ui.feature.home.HomeViewModel

sealed class Screen(val route: String) {
    data object Register : Screen("register")
    data object PhoneNumber : Screen("phone_number")
    data object VerificationCode : Screen("verification_code")
    data object Location : Screen("location")
    data object Home : Screen("home")
}

@Composable
fun SheStoreNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Register.route
    ) {
        composable(Screen.Register.route) {
            val vm: RegisterViewModel = hiltViewModel()
            val state by vm.state.collectAsStateWithLifecycle()

            RegisterScreen(
                state = state,
                onEvent = vm::onEvent,
                onAlreadyHaveAccountClick = {
                    // TODO: navigate to login later
                },
                onContinueToPhoneClick = {
                    navController.navigate(Screen.PhoneNumber.route)
                }
            )
        }

        composable(Screen.PhoneNumber.route) {
            val vm: PhoneNumberViewModel = hiltViewModel()
            val state by vm.state.collectAsStateWithLifecycle()

            PhoneNumberScreen(
                state = state,
                onEvent = vm::onEvent,
                onBackClick = { navController.popBackStack() },
                onContinueClick = {
                    navController.navigate(Screen.VerificationCode.route)
                }
            )
        }

        composable(Screen.VerificationCode.route) {
            val vm: VerificationCodeViewModel = hiltViewModel()
            val state by vm.state.collectAsStateWithLifecycle()

            VerificationCodeScreen(
                state = state,
                onEvent = vm::onEvent,
                onBackClick = { navController.popBackStack() },
                onDoneClick = {
                    // After code is correct, go to Coordinates & Location screen
                    navController.navigate(Screen.Location.route)
                },
                onResendClick = {
                    vm.onEvent(VerificationCodeEvent.ResendCode)
                }
            )
        }

        composable(Screen.Location.route) {
            val vm: LocationViewModel = hiltViewModel()
            val state by vm.state.collectAsStateWithLifecycle()

            LocationScreen(
                state = state,
                onEvent = vm::onEvent,
                onBackClick = { navController.popBackStack() },
                onDoneClick = {
                    // Done with onboarding: go to Home and clear back stack
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onSkipClick = {
                    // Skip location: same behavior as Done
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            val vm: HomeViewModel = hiltViewModel()
            val state by vm.state.collectAsStateWithLifecycle()

            HomeScreen(
                state = state,
                onEvent = vm::onEvent
            )
        }
    }
}
