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
}