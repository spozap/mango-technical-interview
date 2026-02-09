package dev.spozap.core.ui.previews

import dev.spozap.core.model.Product
import dev.spozap.core.model.ProductRating
import dev.spozap.core.model.User

val PRODUCT_PREVIEW = Product(
    id = 1,
    title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
    price = 109.95,
    description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
    category = "category",
    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
    rating = ProductRating(rate = 5.0, count = 100)
)

val PRODUCT_PREVIEW_FAVORITE = Product(
    id = 2,
    title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
    price = 109.95,
    description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
    category = "category",
    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
    rating = ProductRating(rate = 5.0, count = 100),
    isFavourite = true
)

val USER_PREVIEW = User(
    id = "1",
    username = "Prueba",
    email = "prueba@gmail.com",
    phone = "+34 655555555"
)