pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ListMaker"

include(":app")

include(":common-navigation")

include(":core")

include(":navigation")

include(":ui-add-game")

include(":ui-add-movies")

include(":ui-list-create")

include(":ui-game-list-details")

include(":ui-common")

include(":ui-home")

include(":ui-movie-list-details")
