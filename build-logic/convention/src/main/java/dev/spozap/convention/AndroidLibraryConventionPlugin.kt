package dev.spozap.convention

import com.android.build.api.dsl.LibraryExtension
import dev.spozap.convention.config.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import java.io.File

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                testOptions.targetSdk = 36
                testOptions.animationsDisabled = true

                lint.targetSdk = 36

            }

            afterEvaluate {
                val androidTestDir = File(projectDir, "src/androidTest")
                if (!androidTestDir.exists() || androidTestDir.listFiles()?.isEmpty() != false) {
                    tasks.matching { it.name.startsWith("connected") }.configureEach {
                        enabled = false
                        println("⚠️ Disabled ${name} in module ${project.name} (no androidTest sources)")
                    }
                }
            }
        }
    }
}