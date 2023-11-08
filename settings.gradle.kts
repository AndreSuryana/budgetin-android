pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Budgetin"
include(":app")

include(":core:ui")
include(":core:common")
include(":core:domain")
include(":core:data")
include(":core:model")
include(":core:network")
include(":core:database")
include(":core:datastore")

include(":feature:auth")
include(":feature:onboarding")
include(":feature:search")
include(":feature:home")
include(":feature:statistic")
include(":feature:notification")
include(":feature:setting")
