plugins {
    alias(libs.plugins.android.feature)
    alias(libs.plugins.android.library.compose)
}

android {
    namespace = "dev.spozap.feature.products"
}

dependencies {
    implementation(projects.core.data)
}