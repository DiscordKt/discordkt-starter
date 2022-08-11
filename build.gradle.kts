import java.util.Properties

group = "dev.discordkt"
version = "1.0.0"
description = "DiscordKt starter template"

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("me.jakejmattson:DiscordKt:0.23.3")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"

        Properties().apply {
            setProperty("name", project.name)
            setProperty("description", project.description)
            setProperty("version", version.toString())
            setProperty("url", "https://github.com/DiscordKt/ExampleBot")

            store(file("src/main/resources/bot.properties").outputStream(), null)
        }
    }

    shadowJar {
        archiveFileName.set("Bot.jar")
        manifest {
            attributes("Main-Class" to "dev.discordkt.bot.MainKt")
        }
    }
}