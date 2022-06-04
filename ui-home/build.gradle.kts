plugins {
    id("com.adewan.compose.library")
}

android {
    namespace = "com.adewan.listmaker.ui.home"
}

dependencies {
    api(project(":common-navigation"))
    implementation(libs.compose.material3)
}