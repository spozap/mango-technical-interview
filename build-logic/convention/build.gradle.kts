plugins {
    `kotlin-dsl`
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = libs.plugins.android.library.asProvider().get().pluginId
            implementationClass = "dev.spozap.convention.AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.android.library.compose.get().pluginId
            implementationClass = "dev.spozap.convention.AndroidLibraryComposeConventionPlugin"
        }
        register("feature") {
            id = libs.plugins.android.feature.get().pluginId
            implementationClass = "dev.spozap.convention.AndroidFeatureConventionPlugin"
        }
    }
}

dependencies {
    compileOnly(libs.gradle.api)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
}
