plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.andresuryana.budgetin"
    compileSdk = AppConfiguration.compileSdk

    configureDefaultConfig(defaultConfig) {
        it.apply {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }
    }

    configureJvmVersion(compileOptions, kotlinOptions, kotlin)

    configureCompose(buildFeatures, composeOptions)

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    hilt {
        enableAggregatingTask = false
    }
}

dependencies {

    android()
    compose()
    navigation()

    hilt()
    room()
    retrofit()
}