plugins {
    id("com.android.application")
}

android {
    namespace = "com.basit.samplemvvm"
    compileSdk = 34


    buildFeatures {
        viewBinding  = true
    }
    defaultConfig {
        applicationId = "com.basit.samplemvvm"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {

    val lifecycle_version = "2.6.2"
    val room_version = "2.6.0"

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // For ViewModelProviders (deprecated in 2.2.0)
    implementation("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")

    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
}

//dependencies {
//
//    val lifecycle_version = "2.6.2"
//    val arch_version = "2.2.0"
//    val room_version = "2.6.0"
//
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.10.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//
//
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
//    // ViewModel utilities for Compose
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
//    // LiveData
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
////    / For ViewModelProviders (deprecated in 2.2.0)
//     implementation("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
//    implementation("androidx.room:room-ktx:$room_version")
//    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
//    implementation ("androidx.room:room-runtime:2.4.0")
//    annotationProcessor ("androidx.room:room-compiler:2.4.0")
//}