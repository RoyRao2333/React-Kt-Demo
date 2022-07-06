val ktorVersion: String by project
val kotlinVersion: String by project
val logging: String = "2.1.23"
val slf4j: String = "1.7.36"
val connectorJ: String = "8.0.29"
val ktorm: String = "3.5.0"
val hikariCP: String = "5.0.1"

plugins {
    application
    kotlin("jvm") version "1.7.0"
}

group = "com.royrao.reactdemo"
version = "0.0.1"

application {
    mainClass.set("com.royrao.reactdemo.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.github.microutils:kotlin-logging:$logging")
    implementation("org.slf4j:slf4j-simple:$slf4j")
    implementation("mysql:mysql-connector-java:$connectorJ")
    implementation("org.ktorm:ktorm-core:$ktorm")
    implementation("org.ktorm:ktorm-support-mysql:$ktorm")
    implementation("org.ktorm:ktorm-jackson:$ktorm")
    implementation("com.zaxxer:HikariCP:$hikariCP")
}
