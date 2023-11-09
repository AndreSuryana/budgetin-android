import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.CompileOptions
import com.android.build.api.dsl.ComposeOptions
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

object AppConfiguration {

    const val applicationId = "com.andresuryana.budgetin"

    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 34

    const val versionCode = 1
    const val versionName = "1.0"

    val jvmJavaVersion = JavaVersion.VERSION_17
    const val jdkVersion = 17
}

fun configureDefaultConfig(
    defaultConfig: DefaultConfig,
    onAdditionalConfig: ((DefaultConfig) -> Unit)? = null
) {
    defaultConfig.apply {
        minSdk = AppConfiguration.minSdk
        targetSdk = AppConfiguration.targetSdk
    }

    onAdditionalConfig?.invoke(defaultConfig)
}

fun configureJvmVersion(
    compileOptions: CompileOptions,
    kotlinJvmOptions: KotlinJvmOptions,
    kotlinAndroidProject: KotlinAndroidProjectExtension
) {
    compileOptions.apply {
        sourceCompatibility = AppConfiguration.jvmJavaVersion
        targetCompatibility = AppConfiguration.jvmJavaVersion
    }

    kotlinJvmOptions.apply {
        jvmTarget = AppConfiguration.jdkVersion.toString()
    }

    kotlinAndroidProject.apply {
        jvmToolchain(AppConfiguration.jdkVersion)
    }
}

fun configureCompose(
    buildFeatures: BuildFeatures,
    composeOptions: ComposeOptions
) {
    buildFeatures.apply {
        compose = true
    }

    composeOptions.apply {
        kotlinCompilerExtensionVersion = Version.composeVersion
    }
}