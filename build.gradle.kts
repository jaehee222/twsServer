plugins {
	java
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}


dependencies {implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.projectlombok:lombok:1.18.22")
	runtimeOnly("com.mysql:mysql-connector-j")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.cloudinary:cloudinary-http44:1.29.0")
	implementation("org.springframework.boot:spring-boot-starter-mail")
}

tasks.withType<Test> {
	useJUnitPlatform()
}