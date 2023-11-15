plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.andresuryana.budgetin.core.database"
    compileSdk = AppConfiguration.compileSdk

    configureDefaultConfig(defaultConfig) {
        it.apply {
            javaCompileOptions {
                annotationProcessorOptions {
                    arguments += mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                    )
                }
            }
        }
    }

    configureJvmVersion(compileOptions, kotlinOptions, kotlin)
}

dependencies {
    android()
    room()
    hilt()
    testing()
}