plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
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
    data()
    model()
    common()

    android()
    compose()
    testing()
    navigation()

    hilt()
}