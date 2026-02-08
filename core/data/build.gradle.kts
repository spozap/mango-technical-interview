plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "dev.spozap.core.data"
}

dependencies {

    api(projects.core.network)
}