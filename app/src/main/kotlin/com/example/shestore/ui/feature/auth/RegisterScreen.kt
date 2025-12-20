package com.example.shestore.ui.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Icon
import com.example.shestore.R
import com.example.shestore.ui.components.SheRoundedTextField
import com.example.shestore.ui.components.SheTopAppBar
import com.example.shestore.ui.theme.SheStoreTheme
import com.example.shestore.ui.theme.blue

@Composable
fun RegisterScreen(
    state: RegisterUiState,
    onEvent: (RegisterEvent) -> Unit,
    onAlreadyHaveAccountClick: () -> Unit,
    onContinueToPhoneClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SheTopAppBar(
                showBack = true,
                showClose = true,
                onBackClick = { /* TODO: nav back */ },
                onCloseClick = { /* TODO: close */ }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                // Title: New Account
                Text(
                    text = "New Account",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Big blue circle avatar
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(blue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.iconpersonne),
                        contentDescription = "User Avatar",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(44.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                SheRoundedTextField(
                    value = state.fullName,
                    onValueChange = { onEvent(RegisterEvent.FullNameChanged(it)) },
                    placeholder = "Full Name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                SheRoundedTextField(
                    value = state.email,
                    onValueChange = { onEvent(RegisterEvent.EmailChanged(it)) },
                    placeholder = "Email",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                SheRoundedTextField(
                    value = state.password,
                    onValueChange = { onEvent(RegisterEvent.PasswordChanged(it)) },
                    placeholder = "Password",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    isPassword = true,
                    isPasswordVisible = state.isPasswordVisible,
                    onTogglePasswordVisibility = {
                        onEvent(RegisterEvent.TogglePasswordVisibility)
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onEvent(RegisterEvent.Submit)
                        onContinueToPhoneClick()
                    },
                    enabled = !state.isSubmitting,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(26.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue
                    )
                ) {
                    Text(
                        text = "Create an Account",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "By signing up, you agree to our",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Terms ",
                        style = MaterialTheme.typography.bodySmall,
                        color = blue,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "and ",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "Conditions of Use",
                        style = MaterialTheme.typography.bodySmall,
                        color = blue,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "I have an Account",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = blue,
                    modifier = Modifier
                        .clickable { onAlreadyHaveAccountClick() }
                        .padding(bottom = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Register Screen"
)
@Composable
fun RegisterScreenPreview() {
    SheStoreTheme {
        RegisterScreen(
            state = RegisterUiState(),
            onEvent = {},
            onAlreadyHaveAccountClick = {},
            onContinueToPhoneClick = {}
        )
    }
}
