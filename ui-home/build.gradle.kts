plugins {
    id("com.adewan.compose.library")
    id("com.adewan.hilt.plugin")
}

android {
    namespace = "com.adewan.listmaker.ui.home"
}

dependencies {
    api(project(":common-navigation"))
    api(project(":ui-common"))
    api(project(":core"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)

    implementation(libs.compose.material3)
    implementation(libs.compose.tooling.preview)
    debugImplementation(libs.compose.tooling)

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}