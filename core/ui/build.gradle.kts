plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.library.compose)
}

android {
    namespace = "dev.spozap.core.ui"
}

dependencies {
    api(projects.core.designSystem)
    api(projects.core.model)
}