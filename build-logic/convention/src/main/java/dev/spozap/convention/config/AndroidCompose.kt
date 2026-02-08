package dev.spozap.convention.config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension
) {
    commonExtension.apply {
        buildFeatures.compose = true

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            "implementation"(platform(bom))
            "implementation"(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            "androidTestImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
            "androidTestImplementation"(platform(bom))
        }
    }
}