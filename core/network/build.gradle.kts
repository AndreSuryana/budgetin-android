plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.andresuryana.budgetin.core.network"
    compileSdk = AppConfiguration.compileSdk

    configureDefaultConfig(defaultConfig)

    configureJvmVersion(compileOptions, kotlinOptions, kotlin)
}

dependencies {
    android()
    testing()
}