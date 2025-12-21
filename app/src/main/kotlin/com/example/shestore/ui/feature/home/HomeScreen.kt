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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onSearchClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.Black
            )
        }

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

/* ------------ BOTTOM BAR ------------- */

private val ShopYellow = Color(0xFFF6A623)
private val BottomBarGray = Color(0xFFB5B5B5)

@Composable
private fun HomeBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_nav_shop),
                    contentDescription = "Shop"
                )
            },
            label = {
                Text(
                    text = "Shop",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ShopYellow,
                selectedTextColor = ShopYellow,
                unselectedIconColor = BottomBarGray,
                unselectedTextColor = BottomBarGray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_nav_categories),
                    contentDescription = "Categories"
                )
            },
            label = { Text("Categories", fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ShopYellow,
                selectedTextColor = ShopYellow,
                unselectedIconColor = BottomBarGray,
                unselectedTextColor = BottomBarGray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_nav_cart),
                    contentDescription = "Cart"
                )
            },
            label = { Text("Cart", fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ShopYellow,
                selectedTextColor = ShopYellow,
                unselectedIconColor = BottomBarGray,
                unselectedTextColor = BottomBarGray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = selectedIndex == 3,
            onClick = { onItemSelected(3) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_nav_profile),
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile", fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ShopYellow,
                selectedTextColor = ShopYellow,
                unselectedIconColor = BottomBarGray,
                unselectedTextColor = BottomBarGray,
                indicatorColor = Color.Transparent
            )
        )
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
                        color = if (selectedCategory == index) ShopYellow else Color(0xFFBDBDBD),
                        fontWeight = if (selectedCategory == index) FontWeight.SemiBold else FontWeight.Normal,
                        fontSize = 16.sp
                    )
                    if (selectedCategory == index) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(32.dp)
                                .background(ShopYellow)
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
                color = ShopYellow,
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
            painter = painterResource(id = R.drawable.ic_nav_shop),
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
                painter = painterResource(id = R.drawable.ic_nav_shop),
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
