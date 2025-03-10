plugins {
    id("java")
}

group = "github.hyungi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //H2 Database
    runtimeOnly("com.h2database:h2:2.3.232")
    testRuntimeOnly("com.h2database:h2:2.3.232")

    //MyBatis
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")

    //Spring Boot Web
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.3")

    //ORM
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.3")

    //Lombok (컴파일 시에만 필요)
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    //JSON Web Token (JWT)
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    //Junit TEST
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.2")
}

tasks.test {
    useJUnitPlatform()
}