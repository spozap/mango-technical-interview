plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
}

android {
    namespace = "dev.spozap.core.data"
}

dependencies {
    implementation(libs.hilt.android)
    implementation(libs.hilt.compiler)
}