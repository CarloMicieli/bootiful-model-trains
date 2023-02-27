plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.15.0")
    implementation("io.spring.gradle:dependency-management-plugin:1.1.0")
    implementation("me.qoomon:gradle-git-versioning-plugin:6.4.2")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.0.3")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.45.0")
}
