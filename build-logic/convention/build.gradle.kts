plugins {
    `kotlin-dsl`
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
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
        register("hilt") {
            id = libs.plugins.hilt.plugin.get().pluginId
            implementationClass = "dev.spozap.convention.HiltConventionPlugin"
        }
        register("jvmLib") {
            id = libs.plugins.jvm.get().pluginId
            implementationClass = "dev.spozap.convention.JvmLibConventionPlugin"
        }
    }
}

dependencies {
    compileOnly(libs.gradle.api)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradlePlugin)
}
