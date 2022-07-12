plugins { id("com.adewan.compose.library") }

android { namespace = "com.adewan.listmaker.navigation" }

dependencies {
    implementation(project(":common-navigation"))
    implementation(project(":home"))
    implementation(project(":ui-list-create"))
    implementation(project(":ui-game-list-details"))
    implementation(project(":ui-movie-list-details"))
    implementation(project(":ui-add-game"))
    implementation(project(":ui-add-movies"))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.animations)
}
