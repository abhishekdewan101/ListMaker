plugins {
    id("com.adewan.compose.library")
}

android {
    namespace = "com.adewan.listmaker.ui.home"
}

dependencies {
    api(project(":common-navigation"))
    api(project(":ui-common"))
    implementation(libs.compose.material3)
    implementation(libs.compose.tooling.preview)
    debugImplementation(libs.compose.tooling)
}