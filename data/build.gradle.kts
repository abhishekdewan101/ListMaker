plugins {
    id("com.adewan.android.library")
    id("com.squareup.sqldelight")
}

android {
    namespace = "com.adewan.listmaker.data"
}

sqldelight {
    database("ListMakerDB") {
        packageName = "com.adewan.listmaker.db"
    }
}