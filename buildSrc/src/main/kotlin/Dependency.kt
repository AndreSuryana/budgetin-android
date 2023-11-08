import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependency {

    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Version.lifecycle}"
    const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"

    const val composeBom = "androidx.compose:compose-bom:${Version.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composePreviewTool = "androidx.compose.ui:ui-tooling-preview"
    const val composeM3 = "androidx.compose.material3:material3"

    const val composeJUnit = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTool = "androidx.compose.ui:ui-tooling"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest"

    const val jUnit = "junit:junit:${Version.jUnitTest}"
    const val jUnitAndroid = "androidx.test.ext:junit:${Version.jUnitAndroidTest}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"

    const val navigation = "androidx.navigation:navigation-compose:${Version.navigation}"

    const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Version.hilt}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigation}"

    const val room = "androidx.room:room-runtime:${Version.room}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"
}

fun DependencyHandler.android() {
    implementation(
        Dependency.coreKtx,
        Dependency.lifecycle,
        Dependency.activityCompose
    )
}

fun DependencyHandler.compose() {
    implementation(
        platform(Dependency.composeBom),
        Dependency.composeUi,
        Dependency.composeUiGraphics,
        Dependency.composePreviewTool,
        Dependency.composeM3,
    )

    androidTestImplementation(
        platform(Dependency.composeBom),
        Dependency.composeJUnit
    )

    debugImplementation(
        Dependency.composeUiTool,
        Dependency.composeTestManifest
    )
}

fun DependencyHandler.testing() {
    implementation(
        Dependency.jUnit,
        Dependency.jUnitAndroid,
        Dependency.espresso
    )
}

fun DependencyHandler.navigation() {
    implementation(Dependency.navigation)
}

fun DependencyHandler.hilt() {
    implementation(
        Dependency.hilt,
        Dependency.hiltNavigation
        )
    kapt(Dependency.hiltCompiler)
}

fun DependencyHandler.room() {
    implementation(
        Dependency.room,
        Dependency.roomKtx
    )
    kapt(Dependency.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(
        Dependency.retrofit,
        Dependency.gsonConverter,
        Dependency.okHttp,
        Dependency.okHttpLoggingInterceptor
    )
}