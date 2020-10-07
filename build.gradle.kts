import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "me.ddivad"
version = Versions.BOT
description = "discordKt starter template"

plugins {
    kotlin("jvm") version "1.4.10"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("me.jakejmattson:DiscordKt:${Versions.DISCORDKT}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

object Versions {
    const val BOT = "1.0.0"
    const val DISCORDKT = "0.20.0"
}