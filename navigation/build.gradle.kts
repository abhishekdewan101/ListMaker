plugins {
    id("com.adewan.compose.library")
}

android {
    namespace = "com.adewan.listmaker.navigation"
}

dependencies {
    implementation(project(":common-navigation"))
    implementation(project(":ui-home"))
    implementation(project(":ui-list-create"))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.animations)
}