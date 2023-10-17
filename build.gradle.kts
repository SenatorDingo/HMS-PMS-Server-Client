import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	id("org.jetbrains.kotlin.plugin.lombok") version "1.9.10"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "seg3102.group25.wellmeadows"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.junit.platform:junit-platform-suite:1.10.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	testImplementation("io.cucumber:cucumber-java:7.14.0")
	testImplementation("io.cucumber:cucumber-spring:7.14.0")
	testImplementation("io.cucumber:cucumber-junit-platform-engine:7.14.0")

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

configurations {
	all {
		// OPTIONAL: Exclude JUnit 4
		exclude(group = "junit", module = "junit")
		// OPTIONAL: Exclude JUnit 5 vintage engine
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		// OPTIONAL: Exclude JUnit 5 jupiter engine
		exclude(group = "org.junit.jupiter", module = "junit-jupiter-engine")
	}
}
