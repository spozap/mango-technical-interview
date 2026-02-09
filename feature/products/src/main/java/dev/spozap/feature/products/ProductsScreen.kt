package dev.spozap.feature.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.spozap.core.model.Product
import dev.spozap.core.ui.components.LoadingWheel
import dev.spozap.core.ui.components.ProductCard
import dev.spozap.core.ui.previews.PRODUCT_PREVIEW
import dev.spozap.core.ui.previews.PRODUCT_PREVIEW_FAVORITE
import dev.spozap.design_system.MangotechnicalinterviewTheme

@Composable
internal fun ProductsScreenRoute(viewModel: ProductsViewModel = hiltViewModel()) {
    val productsUiState by viewModel.products.collectAsStateWithLifecycle()
    ProductsScreen(uiState = productsUiState, onAddToFavourites = viewModel::addProductToFavourites)
}

@Composable
internal fun ProductsScreen(
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
                    modifier = Modifier.testTag("favoriteIcon"),
                    imageVector = if (it.isFavourite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = if (it.isFavourite) "Producto favorito ${it.title}" else "Producto no favorito",
                    tint = if (it.isFavourite) Color.Red else Color.White,
                )
            }
        })
    }
}

private fun LazyListScope.productsScreenLoading(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
            LoadingWheel()
        }
    }
}

private fun LazyListScope.productsScreenError(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
            Text("ERROR")
        }
    }
}

//region Previews

@Preview(showBackground = true)
@Composable
private fun ProductsScreenSuccessPreview() {
    MangotechnicalinterviewTheme {
        ProductsScreen(
            uiState = ProductsUiState.Success(
                listOf(
                    PRODUCT_PREVIEW,
                    PRODUCT_PREVIEW_FAVORITE
                )
            ),
            onAddToFavourites = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsScreenLoadingPreview() {
    MangotechnicalinterviewTheme {
        ProductsScreen(
            uiState = ProductsUiState.Loading,
            onAddToFavourites = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsScreenErrorPreview() {
    MangotechnicalinterviewTheme {
        ProductsScreen(
            uiState = ProductsUiState.Error(""),
            onAddToFavourites = {}
        )
    }
}

//endregion
