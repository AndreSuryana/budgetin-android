plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.andresuryana.budgetin.core.data"
    compileSdk = AppConfiguration.compileSdk

    configureDefaultConfig(defaultConfig)

    configureJvmVersion(compileOptions, kotlinOptions, kotlin)

    hilt {
        enableAggregatingTask = false
    }
}

dependencies {
    model()

    android()
    hilt()
    testing()
}