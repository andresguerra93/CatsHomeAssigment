plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.agc.catshomeassignmet"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.agc.catshomeassignmet"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //androidTest
        testInstrumentationRunner = "com.agc.catshomeassignmet.CustomTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            //isShrinkResounces = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue("string", "agcname", "CatsApp")

            buildConfigField("String", "BASE_URL", "\"https://urlrelease/\"")
            buildConfigField("String", "BASE_URL_YOUTUBE", "\"https://transcribeapi.centroesteticasmedellin.com/\"")
        }

        getByName("debug"){
            isDebuggable = true

            resValue("string", "agcname", "[DEBUG] CatsApp")
            buildConfigField("String", "BASE_URL", "\"https://urldebug/\"")
            buildConfigField("String", "BASE_URL_YOUTUBE", "\"https://transcribeapi.centroesteticasmedellin.com/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //NavComponent fragment xml
    val navVersion = "2.7.6"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //DaggerHilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.3.1")

    //UnitTesting
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    testImplementation("io.mockk:mockk:1.12.3")

    //UITesting
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")
    androidTestImplementation("androidx.fragment:fragment-testing:1.6.2")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}