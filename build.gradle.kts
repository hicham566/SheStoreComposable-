// build.gradle.kts (project / root)
plugins {
    id("com.android.application") version "8.10.0" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false

    // ➜ ADD THIS LINE: Compose compiler plugin, same version as Kotlin
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.20" apply false

    id("com.google.dagger.hilt.android") version "2.57.2" apply false
}
