import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    sourceSets {
        androidMain.dependencies {
            api("androidx.annotation:annotation:1.10.0")
            api("androidx.activity:activity:1.8.0")
            //  implementation("androidx.activity:activity-ktx:1.8.1")
            //   implementation("androidx.activity:activity:1.8.1")
            //   implementation("androidx.annotation:annotation-experimental:1.8.1")
            
            api("androidx.appcompat:appcompat-resources:1.7.1")
            api("androidx.appcompat:appcompat:1.7.1")
            api("androidx.arch.core:core-runtime:2.2.0")
            api("androidx.asynclayoutinflater:asynclayoutinflater:1.1.0")
            api("androidx.cardview:cardview:1.0.0")
            api("androidx.constraintlayout:constraintlayout:2.2.1")
            api("androidx.coordinatorlayout:coordinatorlayout:1.3.0")
            api("androidx.core:core-ktx:1.18.0")
            api("androidx.core:core:1.18.0")
            //implementation("androidx.cursoradapter:cursoradapter:1.0.0")
            api("androidx.databinding:databinding-runtime:9.2.1")
            api("androidx.customview:customview:1.2.0")
            api("androidx.databinding:databinding-ktx:9.2.1")
            api("androidx.databinding:viewbinding:9.2.1")
            api("androidx.documentfile:documentfile:1.1.0")
            api("androidx.drawerlayout:drawerlayout:1.2.0")
            api("androidx.dynamicanimation:dynamicanimation:1.1.0")
            api("androidx.emoji2:emoji2:1.6.0")
            api("androidx.emoji2:emoji2-views-helper:1.6.0")
            api("androidx.exifinterface:exifinterface:1.4.2")
            api("androidx.fragment:fragment-ktx:1.8.9")
            api("androidx.fragment:fragment:1.8.9")
            //implementation("androidx.interpolator:interpolator:1.0.0")
            //implementation("androidx.legacy:legacy-support-core-ui:1.0.0")
            //implementation("androidx.legacy:legacy-support-core-utils:1.0.0")
            api("androidx.privacysandbox.ads:ads-adservices:1.1.0-beta12")
            api("androidx.privacysandbox.ads:ads-adservices-java:1.1.0-beta12")
            api("androidx.legacy:legacy-support-v4:1.0.0")
            api("androidx.lifecycle:lifecycle-livedata-core-ktx:2.10.0")
            api("androidx.lifecycle:lifecycle-livedata-core:2.10.0")
            api("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")
            api("androidx.lifecycle:lifecycle-livedata:2.10.0")
            api("androidx.lifecycle:lifecycle-process:2.10.0")
            api("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
            api("androidx.lifecycle:lifecycle-runtime:2.10.0")
            api("androidx.lifecycle:lifecycle-service:2.10.0")
            api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
            api("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.10.0")
            api("androidx.lifecycle:lifecycle-viewmodel:2.10.0")
            api("androidx.loader:loader:1.1.0")
            api("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")
            api("androidx.media:media:1.8.0")
            api("androidx.paging:paging-common:3.5.0")
            api("androidx.paging:paging-runtime-ktx:3.5.0")
            api("androidx.paging:paging-runtime:3.5.0")
            api("androidx.preference:preference-ktx:1.2.1")
            api("androidx.preference:preference:1.2.1")
            api("androidx.print:print:1.1.0")
            api("androidx.profileinstaller:profileinstaller:1.4.1")
            api("androidx.recyclerview:recyclerview:1.4.0")
            api("androidx.room:room-runtime:2.8.4")
            api("androidx.savedstate:savedstate-ktx:1.5.0")
            api("androidx.savedstate:savedstate:1.5.0")
            api("androidx.security:security-crypto:1.1.0")
            api("androidx.sqlite:sqlite-framework:2.6.2")
            api("androidx.sqlite:sqlite:2.6.2")
            api("androidx.startup:startup-runtime:1.2.0")
            api("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0")
            api("androidx.tracing:tracing:1.3.0")
            api("androidx.transition:transition:1.7.0")
            api("androidx.vectordrawable:vectordrawable-animated:1.2.0")
            api("androidx.vectordrawable:vectordrawable:1.2.0")
            api("androidx.versionedparcelable:versionedparcelable:1.2.1")
            api("androidx.viewpager2:viewpager2:1.1.0")
            api("androidx.viewpager:viewpager:1.1.0")
            api("androidx.webkit:webkit:1.16.0")
            api("androidx.window:window:1.5.1")
            api("androidx.work:work-runtime-ktx:2.11.2")
            api("androidx.work:work-runtime:2.11.2")
            api("com.google.android.material:material:1.14.0")
            api("com.google.dagger:dagger:2.59.2")
            api("com.journeyapps:zxing-android-embedded:4.3.0")
            api("com.google.firebase:firebase-messaging:25.0.2")
            api("com.google.firebase:firebase-analytics:23.0.0")
            api("com.google.android.gms:play-services-maps:20.0.0")
            api("com.google.android.gms:play-services-analytics-impl:18.2.0")
            api("com.google.android.datatransport:transport-runtime:4.1.1")
            api("com.sun.activation:javax.activation:1.2.0")
            api("com.github.michael-rapp:java-util:2.5.0")
            api("xmlpull:xmlpull:1.1.3.1")
        }
        commonMain.dependencies {
        }

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "shared"
                isStatic = true
            }
        }
    }

}

android {

    namespace = "com.proje.mobilesales"
    compileSdk = 36


    defaultConfig {

        applicationId = "com.proje.mobilesales"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}

dependencies {


  debugImplementation(libs.androidx.compose.ui.tooling)
}

