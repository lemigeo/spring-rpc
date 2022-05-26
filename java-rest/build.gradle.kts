import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.remi.rest"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web:2.6.7")
    implementation("net.devh:grpc-client-spring-boot-starter:2.13.1.RELEASE") {
        exclude("io.grpc:grpc-netty-shaded")
    }
    runtimeOnly("org.springframework.boot:spring-boot-devtools")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.1")


    implementation("io.grpc:grpc-netty:1.46.0")
    implementation("io.grpc:grpc-kotlin-stub:1.2.1")
    implementation("com.google.protobuf:protobuf-kotlin:3.20.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

sourceSets{
    getByName("main"){
        java {
            srcDirs(
                "../java-msg/src/main/java",
                "../java-msg/src/main/grpc",
                "../java-msg/src/main/kotlin",
                "../java-msg/src/main/grpckt"
            )
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(18))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
        jvmTarget = "1.8"
    }
}

tasks.getByName<BootRun>("bootRun") {
    mainClass.set("com.remi.rest.Application")
}