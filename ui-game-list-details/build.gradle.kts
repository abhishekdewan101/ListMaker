plugins {
    id("com.adewan.compose.library")
    id("com.adewan.hilt.plugin")
}

android {
    namespace = "com.adewan.listmaker.ui.game.list.details"
}

dependencies {
    api(project(":common-navigation"))
    api(project(":ui-common"))
    api(project(":core"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)

    implementation(libs.material3)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.kotlinx.coroutines)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
}