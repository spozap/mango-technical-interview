plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt.plugin)
    id("kotlinx-serialization")
}

android {
    namespace = "dev.spozap.core.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit.ktx.serialization.converter)

    api(projects.core.model)
}