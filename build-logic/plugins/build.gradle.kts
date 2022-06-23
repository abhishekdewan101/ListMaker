plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationConvention") {
            id = "com.adewan.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("composeLibraryConvention") {
            id = "com.adewan.compose.library"
            implementationClass = "ComposeLibraryConventionPlugin"
        }
        register("kotlinLibraryConvention") {
            id = "com.adewan.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("androidLibraryConvention") {
            id = "com.adewan.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("hiltPluginConvention") {
            id = "com.adewan.hilt.plugin"
            implementationClass = "HiltLibraryConventionPlugin"
        }
    }
}
