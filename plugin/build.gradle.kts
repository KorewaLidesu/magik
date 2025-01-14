/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Gradle plugin project to get you started.
 * For more details take a look at the Writing Custom Plugins chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.3/userguide/custom_plugins.html
 */

plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`

    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.5.21"

    `kotlin-dsl`

    id("com.gradle.plugin-publish") version "0.14.0"

    `maven-publish`
}

repositories {
    // Use JCenter for resolving dependencies.
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation(platform("org.http4k:http4k-bom:4.9.0.2"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty")
    implementation("org.http4k:http4k-client-apache")
}

group = "com.github.elect86"
version = "0.0.8"

gradlePlugin {
    // Define the plugin
    plugins.create("greeting") {
        id = "elect86.magik"
        displayName = "Maven repository on Github in Kotlin"
        description = "publish directly on your github repository acting as a maven repository"
        implementationClass = "magik.MagikPlugin"
    }
}

pluginBundle {
    website = "https://github.com/elect86/magik"
    vcsUrl = "https://github.com/elect86/magik"
    tags = listOf("github", "repository", "maven", "kotlin", "publish", "publishing")
}

// Add a source set for the functional test suite
val functionalTestSourceSet = sourceSets.create("functionalTest") {}

gradlePlugin.testSourceSets(functionalTestSourceSet)
configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])

// Add a task to run the functional tests
val functionalTest by tasks.registering(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
}

tasks.check {
    // Run the functional tests as part of `check`
    dependsOn(functionalTest)
}

tasks.compileJava {

}