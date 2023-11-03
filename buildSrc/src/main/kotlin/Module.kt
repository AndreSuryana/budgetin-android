import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

// TODO: Define the module here
fun DependencyHandler.example() {
    implementation(project(":module:example"))
}