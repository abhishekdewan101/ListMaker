plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
}

gradlePlugin {
    plugins {
        register("androidApplicationConvention") {
            id = "com.dewan.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}
