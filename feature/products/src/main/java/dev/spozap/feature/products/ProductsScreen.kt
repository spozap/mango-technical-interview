package dev.spozap.feature.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.spozap.core.model.Product
import dev.spozap.core.ui.components.LoadingWheel
import dev.spozap.core.ui.components.ProductCard

@Composable
internal fun ProductsScreenRoute(viewModel: ProductsViewModel = hiltViewModel()) {
    val productsUiState by viewModel.products.collectAsStateWithLifecycle()
    ProductsScreen(uiState = productsUiState, onAddToFavourites = viewModel::addProductToFavourites)
}

@Composable
private fun ProductsScreen(
    uiState: ProductsUiState,
    onAddToFavourites: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        when (uiState) {
            is ProductsUiState.Success -> productsScreenSuccess(uiState.products, onAddToFavourites)
            is ProductsUiState.Loading -> productsScreenLoading()
            is ProductsUiState.Error -> productsScreenError()
        }
    }
}

private fun LazyListScope.productsScreenSuccess(
    products: List<Product>,
    onAddToFavourites: (Product) -> Unit
) {
    items(items = products, key = { it.id }) {
        ProductCard(it, actions = {
            IconButton(
                onClick = { onAddToFavourites(it) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = if (it.isFavourite) Color.Red else Color.Unspecified,
                )
            }
        })
    }
}

private fun LazyListScope.productsScreenLoading(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            LoadingWheel()
        }
    }
}

private fun LazyListScope.productsScreenError(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text("ERROR", modifier = Modifier.testTag("ProfileError"))
        }
    }
}