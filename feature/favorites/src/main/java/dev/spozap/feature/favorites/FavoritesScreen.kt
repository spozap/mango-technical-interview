package dev.spozap.feature.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.spozap.core.model.Product
import dev.spozap.core.ui.components.LoadingWheel
import dev.spozap.core.ui.components.ProductCard

@Composable
internal fun FavoritesScreenRoute(viewModel: FavoritesViewModel = hiltViewModel()) {
    val uiState by viewModel.products.collectAsStateWithLifecycle()
    FavoritesScreen(uiState)
}

@Composable
private fun FavoritesScreen(uiState: FavoritesUiState, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        when (uiState) {
            is FavoritesUiState.Success -> {
                favoritesScreenSuccess(uiState.products)
            }

            is FavoritesUiState.Error -> {}
            FavoritesUiState.Loading -> {}
            FavoritesUiState.Empty -> {}
        }
    }
}

private fun LazyListScope.favoritesScreenSuccess(products: List<Product>) {
    items(items = products, key = { it.id }) {
        ProductCard(it)
    }
}

private fun LazyListScope.favoritesScreenError() {

}

private fun LazyListScope.favoritesScreenLoading(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            LoadingWheel()
        }
    }
}

private fun LazyListScope.favoritesScreenEmpty() {

}