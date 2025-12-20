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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shestore.R
import com.example.shestore.ui.components.SheRoundedTextField
import com.example.shestore.ui.components.SheTopAppBar
import com.example.shestore.ui.theme.Primary
import com.example.shestore.ui.theme.SheStoreTheme
import com.example.shestore.ui.theme.blue

@Composable
fun PhoneNumberScreen(
    state: PhoneNumberUiState,
    onEvent: (PhoneNumberEvent) -> Unit,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SheTopAppBar(
                showBack = true,
                showClose = true,
                onBackClick = onBackClick  ,
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


                Spacer(modifier = Modifier.height(32.dp))

                // Title
                Text(
                    text = "Start with\nPhone Number",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Subtitle
                Text(
                    text = "You will get a code via SMS.\nOperator rates may apply.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Phone input – pill like in UI kit
                SheRoundedTextField(
                    value = state.phone,
                    onValueChange = { onEvent(PhoneNumberEvent.PhoneChanged(it)) },
                    placeholder = "Phone",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Continue button
                Button(
                    onClick = {
                        onEvent(PhoneNumberEvent.Submit)
                        onContinueClick()
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
                        text = "Continue",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Terms text
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

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Phone Number Screen")
@Composable
fun PhoneNumberScreenPreview() {
    SheStoreTheme {
        PhoneNumberScreen(
            state = PhoneNumberUiState(phone = "+375 29 7302050"),
            onEvent = {},
            onBackClick = {},
            onContinueClick = {}
        )
    }
}
