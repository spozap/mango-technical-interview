plugins {
    alias(libs.plugins.android.feature)
    alias(libs.plugins.android.library.compose)
}

android {
    namespace = "dev.spozap.feature.profile"
}

dependencies {
    implementation(projects.core.data)

    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.compose.ui.test.manifest)
}