plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.andresuryana.budgetin.feature.search"
}

dependencies {
    android()
    compose()
    testing()
    navigation()
}