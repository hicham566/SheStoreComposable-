package com.example.shestore.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shestore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheTopAppBar(
    modifier: Modifier = Modifier,
    showBack: Boolean = true,
    showClose: Boolean = true,
    onBackClick: () -> Unit,          // <- no-op removed so you MUST pass it
    onCloseClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = onBackClick) {     // <- uses onBackClick
                    Icon(
                        painter = painterResource(id = R.drawable.iconback),
                        contentDescription = "Back",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        title = {
            Icon(
                painter = painterResource(id = R.drawable.iconshe),
                contentDescription = "She.",
                modifier = Modifier.size(34.dp)
            )
        },
        actions = {
            if (showClose) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.iconclose),
                        contentDescription = "Close",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    )
}
