pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WeatherNowAndLater"
include(":app")
include(":features:current_weather:data")
include(":features:seven_days_forecast:data")
include(":features:current_weather:domain")
include(":features:current_weather:presentation")
include(":features:seven_days_forecast:domain")
include(":features:seven_days_forecast:presentation")
include(":core:utils")
include(":features:city_input:data")
include(":features:city_input:domain")
include(":features:city_input:presentation")
