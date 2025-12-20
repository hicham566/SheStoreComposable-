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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shestore.R
import com.example.shestore.ui.components.SheTopAppBar
import com.example.shestore.ui.theme.SheStoreTheme
import com.example.shestore.ui.theme.blue

@Composable
fun LocationScreen(
    state: LocationUiState,
    onEvent: (LocationEvent) -> Unit,
    onBackClick: () -> Unit,
    onDoneClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SheTopAppBar(
                showBack = true,
                showClose = true,
                onBackClick = onBackClick   ,
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
                    text = "Coordinates\nand Location",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Subtitle
                Text(
                    text = "Choose your location to\ncalculate your shipping fees",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Location pill
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0xFFF5F5F5))
                        .clickable {
                            // open map / picker later
                            onEvent(LocationEvent.ChangeLocationClicked)
                        }
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Location:",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFB0B0B0)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        // Country name in blue
                        Text(
                            text = state.countryName,
                            style = MaterialTheme.typography.bodyMedium,
                            color = blue,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // Arrow icon placeholder (replace with your icon drawable if you want)
                        Text(
                            text = "➤",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Done button
                Button(
                    onClick = {
                        onEvent(LocationEvent.Submit)
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

                // "Change your Location"
                Text(
                    text = "Change your Location",
                    style = MaterialTheme.typography.bodyMedium,
                    color = blue,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        onEvent(LocationEvent.ChangeLocationClicked)
                    }
                )

                // Push Skip button to bottom
                Spacer(modifier = Modifier.weight(1f))

                // Skip button at bottom
                Button(
                    onClick = {
                        onEvent(LocationEvent.Skip)
                        onSkipClick()
                    },
                    modifier = Modifier
                        .width(140.dp)
                        .height(44.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF444444),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Skip",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Location Screen")
@Composable
fun LocationScreenPreview() {
    SheStoreTheme {
        LocationScreen(
            state = LocationUiState(countryName = "Morocco"),
            onEvent = {},
            onBackClick = {},
            onDoneClick = {},
            onSkipClick = {}
        )
    }
}
