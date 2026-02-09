plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "dev.spozap.core.testing"
}

dependencies {
    api(libs.junit)
    api(libs.mockk.android)
    api(libs.mockk.agent)
    api(libs.turbine)
    api(libs.kotlinx.coroutines.test)
}