import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.kotlin.symbol.processing)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "com.data.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        val apiKey = properties.getProperty("API_KEY", "your_API_key")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        manifestPlaceholders["API_KEY"] = apiKey
    }

    buildFeatures {
        buildConfig = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.androidx.compose.bom))

    // dagger hilt
    implementation(libs.bundles.dagger.hilt)
    kapt(libs.com.hilt.android.compiler)

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

}