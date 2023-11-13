plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.andresuryana.budgetin.feature.notification"
    compileSdk = AppConfiguration.compileSdk

    configureDefaultConfig(defaultConfig)

    configureJvmVersion(compileOptions, kotlinOptions, kotlin)

    configureCompose(buildFeatures, composeOptions)
}

dependencies {
    ui()
    model()

    android()
    compose()
    testing()
    navigation()

    hilt()
}