import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

/**
 * Core modules
 */
fun DependencyHandler.ui() {
    implementation(project(":core:ui"))
}

fun DependencyHandler.common() {
    implementation(project(":core:common"))
}

fun DependencyHandler.domain() {
    implementation(project(":core:domain"))
}

fun DependencyHandler.data() {
    implementation(project(":core:data"))
}

fun DependencyHandler.model() {
    implementation(project(":core:model"))
}

fun DependencyHandler.network() {
    implementation(project(":core:network"))
}

fun DependencyHandler.database() {
    implementation(project(":core:database"))
}

fun DependencyHandler.datastore() {
    implementation(project(":core:datastore"))
}


/**
 * Feature modules
 */
fun DependencyHandler.auth() {
    implementation(project(":feature:auth"))
}

fun DependencyHandler.onboarding() {
    implementation(project(":feature:onboarding"))
}

fun DependencyHandler.search() {
    implementation(project(":feature:search"))
}

fun DependencyHandler.home() {
    implementation(project(":feature:home"))
}

fun DependencyHandler.statistic() {
    implementation(project(":feature:statistic"))
}

fun DependencyHandler.notification() {
    implementation(project(":feature:notification"))
}

fun DependencyHandler.setting() {
    implementation(project(":feature:setting"))
}
