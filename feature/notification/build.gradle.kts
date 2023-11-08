plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.andresuryana.notification"
}

dependencies {
    android()
    compose()
    testing()
    navigation()
}