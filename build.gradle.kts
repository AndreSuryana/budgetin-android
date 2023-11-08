// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Version.androidApplication apply false
    id("org.jetbrains.kotlin.android") version Version.kotlin apply false
    id("com.google.dagger.hilt.android") version Version.hilt apply false
    id("com.android.library") version Version.androidLibrary apply false
}