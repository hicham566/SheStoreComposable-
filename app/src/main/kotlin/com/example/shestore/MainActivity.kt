package com.example.shestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import dagger.hilt.android.AndroidEntryPoint
import com.example.shestore.ui.navigation.SheStoreNavGraph
import com.example.shestore.ui.theme.SheStoreTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Make status bar itself transparent
        window.statusBarColor = android.graphics.Color.TRANSPARENT

        // 🔹 Tell Android to use DARK icons on the status bar (because bg is light)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true           // dark icons on light bg
            isAppearanceLightNavigationBars = true       // optional, for nav bar
        }

        setContent {
            SheStoreAppRoot()
        }
    }
}

@Composable
fun SheStoreAppRoot() {
    SheStoreTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SheStoreNavGraph()
        }
    }
}