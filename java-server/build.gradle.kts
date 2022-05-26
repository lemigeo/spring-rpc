import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"
    id ("org.jetbrains.kotlin.plugin.noarg") version "1.6.21"
}

noArg {
    //auto-generated the NoArgument constructor in Entity annotation
    annotation("javax.persistence.Entity")
}

//open class
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

group = "com.remi.server"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.7")
    implementation("net.devh:grpc-server-spring-boot-starter:2.13.1.RELEASE"){
        exclude("io.grpc:grpc-netty-shaded")
    }

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.grpc:grpc-netty:1.46.0")
    implementation("io.grpc:grpc-kotlin-stub:1.2.1")
    implementation("com.google.protobuf:protobuf-kotlin:3.20.1")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation(kotlin("test"))
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