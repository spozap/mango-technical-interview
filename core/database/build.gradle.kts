plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.room.plugin)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "dev.spozap.core.database"
}