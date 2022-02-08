package me.ddivad.starter.services

import me.ddivad.starter.dataclasses.Configuration
import me.jakejmattson.discordkt.Discord
import me.jakejmattson.discordkt.annotations.Service
import me.jakejmattson.discordkt.extensions.toTimeString
import java.util.*
import kotlin.time.ExperimentalTime

@Service
class BotStatsService(private val configuration: Configuration, private val discord: Discord) {
    private var startTime: Date = Date()

    val uptime: String
        get() = ((Date().time - startTime.time) / 1000).toTimeString()

    @OptIn(ExperimentalTime::class)
    val ping: String
        get() = "${discord.kord.gateway.averagePing}"
}