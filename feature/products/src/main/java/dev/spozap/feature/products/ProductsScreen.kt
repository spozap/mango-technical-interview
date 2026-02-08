package dev.spozap.feature.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    ProductsScreen(uiState = productsUiState)
}

@Composable
private fun ProductsScreen(uiState: ProductsUiState, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        when (uiState) {
            is ProductsUiState.Success -> productsScreenSuccess(uiState.products)
            is ProductsUiState.Loading -> productsScreenLoading()
            is ProductsUiState.Error -> productsScreenError()
        }
    }
}

private fun LazyListScope.productsScreenSuccess(products: List<Product>) {
    items(items = products, key = { it.id }) {
        ProductCard(it)
    }
}

private fun LazyListScope.productsScreenLoading(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            LoadingWheel(modifier = Modifier.testTag("ProfileLoading"))
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