buildscript {
	val androidGradleVersion: String by extra("7.2.1")
	val kotlinVersion: String by extra("1.7.0")
	val hiltVersion: String by extra("2.42")
	val navigationVersion: String by extra("2.4.2")

	repositories {
		google()
		mavenCentral()
		maven { setUrl("https://jitpack.io") }
	}

	dependencies {
		classpath("com.android.tools.build:gradle:$androidGradleVersion")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
		classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
		classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
	}
}

allprojects {
	repositories {
		google()
		mavenCentral()
		maven { setUrl("https://jitpack.io") }
	}
}

tasks.register<Delete>("clean").configure {
	delete(rootProject.buildDir)
}
