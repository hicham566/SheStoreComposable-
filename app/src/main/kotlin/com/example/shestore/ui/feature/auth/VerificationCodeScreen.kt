package com.example.shestore.ui.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shestore.R
import com.example.shestore.ui.components.SheTopAppBar
import com.example.shestore.ui.theme.SheStoreTheme
import com.example.shestore.ui.theme.blue

@Composable
fun VerificationCodeScreen(
    state: VerificationCodeUiState,
    onEvent: (VerificationCodeEvent) -> Unit,
    onBackClick: () -> Unit,
    onDoneClick: () -> Unit,
    onResendClick: () -> Unit
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

                // Main blue-bordered box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = blue,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Title
                        Text(
                            text = "Verification\nCode",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Subtitle
                        Text(
                            text = "Enter the verification code\nto complete Sign in.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Secure Code pill input
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color(0xFFF5F5F5))
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Secure Code:",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFFB0B0B0)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                BasicTextField(
                                    value = state.code,
                                    onValueChange = {
                                        onEvent(VerificationCodeEvent.CodeChanged(it))
                                    },
                                    textStyle = TextStyle(
                                        color = blue,
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier.weight(1f),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            onEvent(VerificationCodeEvent.Submit)
                                            onDoneClick()
                                        }
                                    ),
                                    singleLine = true
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Done button
                        Button(
                            onClick = {
                                onEvent(VerificationCodeEvent.Submit)
                                onDoneClick()
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
                                text = "Done",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Resend Code
                        Text(
                            text = "Resend Code?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = blue,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable { onResendClick() }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Verification Code Screen")
@Composable
fun VerificationCodeScreenPreview() {
    SheStoreTheme {
        VerificationCodeScreen(
            state = VerificationCodeUiState(code = "264790"),
            onEvent = {},
            onBackClick = {},
            onDoneClick = {},
            onResendClick = {}
        )
    }
}
