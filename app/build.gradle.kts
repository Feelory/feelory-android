plugins {
    alias(libs.plugins.feelory.android.application)
    alias(libs.plugins.feelory.android.application.compose)
//    alias(libs.plugins.feelory.android.library.compose)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.feelory.feelory"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.feelory.feelory"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.graphics.core)
    implementation(libs.androidx.graphics.path)
    implementation(libs.androidx.graphics.shapes)
    implementation(project(":feature:home"))
    implementation(project(":feature:feed"))
    implementation(project(":feature:log"))
    implementation(project(":feature:mypage"))
}
