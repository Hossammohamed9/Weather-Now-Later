plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.kotlin.symbol.processing)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.features.current_weather.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":features:current_weather:domain"))

    implementation(libs.bundles.base)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // google places api
    implementation(platform(libs.jetbrains.kotlin.bom))
    implementation(libs.google.places)

    // dagger hilt
    implementation(libs.bundles.dagger.hilt)
    kapt(libs.com.hilt.android.compiler)

    // retrofit
    implementation(libs.bundles.retrofit)

    // room
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
}