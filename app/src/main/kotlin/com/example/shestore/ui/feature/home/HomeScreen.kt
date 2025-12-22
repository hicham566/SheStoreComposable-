package com.example.shestore.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shestore.R
import com.example.shestore.ui.theme.SheStoreTheme
import com.example.shestore.ui.theme.blue

@Composable
fun HomeScreen(
    state: HomeUiState,
    onEvent: (HomeEvent) -> Unit
) {
    // Local UI-only state
    val categories = listOf("T-Shirts", "Pants", "Jackets", "Shoes")
    var selectedCategory by remember { mutableStateOf(0) }
    var selectedBottomItem by remember { mutableStateOf(0) } // 0 = Shop

    val products = remember { dummyProducts } // TODO plug real data from state later

    Scaffold(
        topBar = {
            HomeTopBar(
                onMenuClick = { /* TODO */ },
                onSearchClick = { /* TODO */ },
                onProfileClick = { /* TODO */ }
            )
        },
        bottomBar = {
            HomeBottomBar(
                selectedIndex = selectedBottomItem,
                onItemSelected = { selectedBottomItem = it }
            )
        }
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            products = products,
            onProductClick = { product ->
                onEvent(HomeEvent.OnProductClick(product.id.toString()))
            },
            onSeeAllClick = {
                onEvent(HomeEvent.OnSeeAllNewArrivals)
            }
        )
    }
}

/* ------------ TOP BAR ------------- */

@Composable
private fun HomeTopBar(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    // Color you want for the whole top area (status bar + app bar)
    val topBarColor = Color(0xFFF4F4F4)   // same grey you use for screen bg

    Surface(
        color = topBarColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()               // <- leaves space for status bar
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Menu icon
            IconButton(onClick = onMenuClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Search icon
            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = Color.Black
                )
            }

            // Avatar
            Box(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { onProfileClick() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile_placeholder),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF3B30))
                )
            }
        }
    }
}


/* ------------ BOTTOM BAR -------------*/

private val BottomIconGray = Color(0xFFBDBDBD)

private data class BottomNavItemData(
    val label: String,
    val iconRes: Int
)
//good now i want to display name of icon on nav bar with animation from left to right
@Composable
private fun HomeBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        BottomNavItemData("Shop",        R.drawable.ic_nav_shop),
        BottomNavItemData("Categories",  R.drawable.ic_nav_categories),
        BottomNavItemData("Bag",         R.drawable.ic_nav_cart),
        BottomNavItemData("Saved",       R.drawable.ic_nav_bookmark),
        BottomNavItemData("Profile",     R.drawable.ic_nav_profile)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF4F4F4))
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(40.dp),
            shadowElevation = 10.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEachIndexed { index, item ->
                    PillBottomNavItem(
                        iconRes = item.iconRes,
                        label = item.label,
                        selected = index == selectedIndex,
                        onClick = { onItemSelected(index) }
                    )
                }
            }
        }
    }
}

@Composable
private fun PillBottomNavItem(
    iconRes: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = if (selected) blue else BottomIconGray,
            modifier = Modifier.size(22.dp)
        )

        if (selected) {
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                color = blue,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

/* ------------ MAIN CONTENT ------------- */

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    categories: List<String>,
    selectedCategory: Int,
    onCategorySelected: (Int) -> Unit,
    products: List<UiProduct>,
    onProductClick: (UiProduct) -> Unit,
    onSeeAllClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(Color(0xFFF4F4F4))
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "What are you\nlooking for ?",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp
            ),
            color = Color(0xFF333333),
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            categories.forEachIndexed { index, label ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onCategorySelected(index) }
                ) {
                    Text(
                        text = label,
                        color = if (selectedCategory == index) blue else Color(0xFFBDBDBD),
                        fontWeight = if (selectedCategory == index) FontWeight.SemiBold else FontWeight.Normal,
                        fontSize = 16.sp
                    )
                    if (selectedCategory == index) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(32.dp)
                                .background(blue)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(2) {
                PromoCard()
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New Arrivals",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color(0xFF333333)
            )
            Text(
                text = "See All",
                style = MaterialTheme.typography.bodyMedium,
                color = blue,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onClick = { onProductClick(product) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/* ------------ PROMO CARD ------------- */

@Composable
private fun PromoCard() {
    Box(
        modifier = Modifier
            .width(260.dp)
            .height(170.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_promo_placeholder),
            contentDescription = "Promo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .padding(12.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sale",
                color = Color(0xFFE53935),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                fontSize = 12.sp
            )
        }
    }
}

/* ------------ PRODUCT CARD ------------- */

data class UiProduct(
    val id: Int,
    val name: String,
    val price: String,
    val oldPrice: String?,
    val rating: Float,
    val ratingCount: Int
)

@Composable
private fun ProductCard(
    product: UiProduct,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(210.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_product_placeholder),
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "⋮",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                fontSize = 18.sp,
                color = Color(0xFF555555)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = Color(0xFF333333),
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF222222)
            )

            if (product.oldPrice != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = product.oldPrice,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFB0B0B0)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "★ ${product.rating}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFF6A623)
            )
            Text(
                text = " (${product.ratingCount} ratings)",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF9E9E9E)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE3F6FF))
        ) {
            Text(
                text = "Free Shipping",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF039BE5),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

/* ------------ DUMMY DATA + PREVIEW ------------- */

private val dummyProducts = listOf(
    UiProduct(
        id = 1,
        name = "Tied Back Plunge Dress",
        price = "$ 150.00",
        oldPrice = "$ 280.00",
        rating = 3.9f,
        ratingCount = 35
    ),
    UiProduct(
        id = 2,
        name = "Simone Snap Front Pant",
        price = "$ 240.00",
        oldPrice = "$ 299.00",
        rating = 4.0f,
        ratingCount = 42
    )
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    SheStoreTheme {
        HomeScreen(
            state = HomeUiState(), // your existing state class
            onEvent = {}
        )
    }
}
