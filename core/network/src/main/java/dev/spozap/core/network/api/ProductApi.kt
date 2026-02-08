package dev.spozap.core.network.api

import dev.spozap.core.network.dto.responses.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>
}