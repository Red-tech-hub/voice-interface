plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm("desktop") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    sourceSets {
        desktopMain.dependencies {
            implementation(project(":shared"))
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            // Koin Compose for desktop DI
            // implementation(libs.koin.compose) -- use core for now
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.voiceinterface.desktop.MainKt"

        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
            )
            packageName = "voice-interface"
            packageVersion = "0.1.0"
            description = "Voice Interface — voice-assisted AI agent"
            vendor = "Voice Interface"

            linux {
                iconFile.set(project.file("src/main/resources/icon.png"))
            }
        }
    }
}
