import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(vararg dependencyNotation: Any) {
    for (dependency in dependencyNotation) {
        add("implementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(vararg dependencyNotation: Any) {
    for (dependency in dependencyNotation) {
        add("debugImplementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(vararg dependencyNotation: Any) {
    for (dependency in dependencyNotation) {
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(vararg dependencyNotation: Any) {
    for (dependency in dependencyNotation) {
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.kapt(vararg dependencyNotation: Any) {
    for (dependency in dependencyNotation) {
        add("kapt", dependency)
    }
}