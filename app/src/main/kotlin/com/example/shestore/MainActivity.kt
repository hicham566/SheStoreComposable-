package com.example.shestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.shestore.ui.navigation.SheStoreNavGraph
import com.example.shestore.ui.theme.SheStoreTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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