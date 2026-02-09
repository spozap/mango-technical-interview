plugins {
    alias(libs.plugins.android.feature)
    alias(libs.plugins.android.library.compose)
}

android {
    namespace = "dev.spozap.feature.favorites"
}

dependencies {
    implementation(projects.core.data)
}