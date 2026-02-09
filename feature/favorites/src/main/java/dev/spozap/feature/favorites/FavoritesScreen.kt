package dev.spozap.feature.favorites

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.spozap.core.model.Product
import dev.spozap.core.ui.components.LoadingWheel
import dev.spozap.core.ui.components.ProductCard
import dev.spozap.core.ui.previews.PRODUCT_PREVIEW
import dev.spozap.design_system.MangotechnicalinterviewTheme

@Composable
internal fun FavoritesScreenRoute(viewModel: FavoritesViewModel = hiltViewModel()) {
    val uiState by viewModel.products.collectAsStateWithLifecycle()
    FavoritesScreen(uiState)
}

@Composable
internal fun FavoritesScreen(uiState: FavoritesUiState, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        when (uiState) {
            is FavoritesUiState.Success -> {
                favoritesScreenSuccess(uiState.products)
            }

            is FavoritesUiState.Error -> {
                favoritesScreenError()
            }

            FavoritesUiState.Loading -> {
                favoritesScreenLoading()
            }

            FavoritesUiState.Empty -> {
                favoritesScreenEmpty()
            }
        }
    }
}

private fun LazyListScope.favoritesScreenSuccess(products: List<Product>) {
    items(items = products, key = { it.id }) {
        ProductCard(it)
    }
}

private fun LazyListScope.favoritesScreenEmpty(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay productos añadidos")
        }
    }
}

private fun LazyListScope.favoritesScreenError() {
    item {
        Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
            Text("ERROR", modifier = Modifier.testTag("ProfileError"))
        }
    }
}

private fun LazyListScope.favoritesScreenLoading(modifier: Modifier = Modifier) {
    item {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            LoadingWheel()
        }
    }
}

//region Previews

@Preview(showBackground = true)
@Composable
private fun FavoritesScreenSuccessPreview() {
    MangotechnicalinterviewTheme {
        FavoritesScreen(
            uiState = FavoritesUiState.Success(
                products = listOf(PRODUCT_PREVIEW)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesScreenLoadingPreview() {
    MangotechnicalinterviewTheme {
        FavoritesScreen(
            uiState = FavoritesUiState.Loading
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesScreenErrorPreview() {
    MangotechnicalinterviewTheme {
        FavoritesScreen(
            uiState = FavoritesUiState.Error("")
        )
    }
}

//endregion