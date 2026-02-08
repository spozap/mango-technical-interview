plugins {
    alias(libs.plugins.android.feature)
    alias(libs.plugins.android.library.compose)
}

android {
    namespace = "dev.spozap.feature.profile"
}

dependencies {
    implementation(projects.core.data)
}