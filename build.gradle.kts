plugins {
    // Android Application Plugin
    id("com.android.application") version "8.2.0" apply false

    // Kotlin Plugin
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false

    // Google Services Plugin (Updated to match your screenshot)
    id("com.google.gms.google-services") version "4.4.4" apply false

    // KSP Plugin (Required for Room Database)
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}