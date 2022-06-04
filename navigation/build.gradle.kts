plugins {
    id("com.adewan.compose.library")
}

android {
    namespace = "com.adewan.listmaker.navigation"
}

dependencies {
    implementation(project(":common-navigation"))
    implementation(project(":ui-home"))
    implementation(libs.compose.navigation)
}