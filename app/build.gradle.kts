plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.andresuryana.budgetin"
    compileSdk = AppConfiguration.compileSdk

    configureDefaultConfig(defaultConfig) {
        it.apply {
            applicationId = AppConfiguration.applicationId

            versionCode = AppConfiguration.versionCode
            versionName = AppConfiguration.versionName

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

    ui()
    data()
    database()

    home()
    search()
    statistic()
    notification()
    setting()

    android()
    compose()
    navigation()

    hilt()
    room()
    retrofit()
}