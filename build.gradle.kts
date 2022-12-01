import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

dependencies {

    testImplementation(kotlin("test"))
    // include for JVM target
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.8.0")
}

tasks.test {
    useJUnitPlatform()
}


application {
    mainClass.set("MainKt")
}