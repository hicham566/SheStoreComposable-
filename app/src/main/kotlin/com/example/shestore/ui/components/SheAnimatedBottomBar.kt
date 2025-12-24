package com.example.shestore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle
import com.example.shestore.ui.feature.home.BottomNavItem
import com.example.shestore.ui.theme.blue // your primary blue

// AND ALSO (these are from the sample app, not the lib)
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem

@Composable
fun SheAnimatedBottomBar(
    items: List<BottomNavItem>,
    currentRoute: String,
    onItemSelected: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {

    val selectedIndex = remember(currentRoute) {
        items.indexOfFirst { it.route == currentRoute }.takeIf { it >= 0 } ?: 0
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedBottomBar(
            selectedItem = selectedIndex,
            itemSize = items.size,
            containerColor = Color.White,
            contentColor = blue,          // icon + label color
            indicatorColor = blue,        // pill color
            indicatorStyle = IndicatorStyle.FILLED,
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .clip(RoundedCornerShape(36.dp))
        ) {
            items.forEachIndexed { index, item ->
                BottomBarItem(
                    selected = index == selectedIndex,
                    onClick = {
                        if (currentRoute != item.route) {
                            onItemSelected(item)
                        }
                    },
                    imageVector = ImageVector.vectorResource(id = item.iconRes),
                    label = item.label,
                    containerColor = Color.Transparent
                )
            }
        }
    }
}
