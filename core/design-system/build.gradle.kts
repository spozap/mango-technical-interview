plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.library.compose)
}

android {
    namespace = "dev.spozap.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.material3)
}