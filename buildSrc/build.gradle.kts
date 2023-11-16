plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(
        group = "com.android.tools.build",
        name = "gradle",
        version = "8.1.3"
    )
    implementation(
        group = "org.jetbrains.kotlin",
        name = "kotlin-gradle-plugin",
        version = "1.9.10"
    )

    // Added javapoet dependency for Dagger Hilt. Because we configure dependencies using
    // buildScr folder, javapoet implementation is required here. Otherwise, we run into missing
    // javapoet dependency in compilation process.
    implementation("com.squareup:javapoet:1.13.0")
}