plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.kotlin.symbol.processing)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.data"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // dagger hilt
    implementation(libs.bundles.dagger.hilt)
    kapt(libs.com.hilt.android.compiler)
    annotationProcessor(libs.com.hilt.compiler)

    // google places api
    implementation(platform(libs.jetbrains.kotlin.bom))
    implementation(libs.google.places)

    // retrofit
    implementation(libs.bundles.retrofit)

    // room
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    // unit testing
    testImplementation(libs.bundles.unite.testing)

    androidTestImplementation(libs.bundles.instrumented.test)
}