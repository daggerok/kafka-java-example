plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.11")
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    // kafka
    val kafkaVersion: String by lazy { "2.1.1" }
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
}

application {
    mainClassName = System.getProperty("mainClass", "com.github.daggerok.AppKt")
}

defaultTasks("build", "installDist")

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
}

tasks {
    "wrapper"(Wrapper::class) {
        gradleVersion = "5.2.1"
    }
}

// gradle tests output stdOut workaround
tasks {
    test {
        testLogging {
            showExceptions = true
            showStandardStreams = true
            useJUnitPlatform()
            useJUnit()
        }
    }
}
