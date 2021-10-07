package me.ddivad.starter

import dev.kord.common.annotation.KordPreview
import dev.kord.common.kColor
import dev.kord.core.supplier.EntitySupplyStrategy
import dev.kord.gateway.PrivilegedIntent
import me.ddivad.starter.dataclasses.Configuration
import me.ddivad.starter.dataclasses.Permissions
import me.ddivad.starter.services.BotStatsService
import me.jakejmattson.discordkt.api.dsl.bot
import me.jakejmattson.discordkt.api.extensions.addInlineField
import java.awt.Color

@KordPreview
@PrivilegedIntent
suspend fun main() {
    val token = System.getenv("BOT_TOKEN") ?: null
    val prefix = System.getenv("DEFAULT_PREFIX") ?: "<none>"

    require(token != null) { "Expected the bot token as an environment variable" }

    bot(token) {
        val configuration = data("config/config.json") { Configuration() }

        prefix {
            guild?.let { configuration[it.id]?.prefix } ?: prefix
        }

        configure {
            allowMentionPrefix = true
            commandReaction = null
            theme = Color.MAGENTA
            permissions(Permissions.NONE)
        }

        mentionEmbed {
            val botStats = it.discord.getInjectionObjects(BotStatsService::class)
            val channel = it.channel
            val self = channel.kord.getSelf()

            color = it.discord.configuration.theme

            thumbnail {
                url = self.avatar.url
            }

            field {
                name = self.tag
                value = "A template for a DiscordKt bot. Change this for your bot's description."
            }

            addInlineField("Prefix", it.prefix())
            addInlineField("Contributors", "ddivad#0001")

            val kotlinVersion = KotlinVersion.CURRENT
            val versions = it.discord.versions
            field {
                name = "Build Info"
                value = "```" +
                        "Version:   1.0.0\n" +
                        "DiscordKt: ${versions.library}\n" +
                        "Kotlin:    $kotlinVersion" +
                        "```"
            }

            field {
                name = "Uptime"
                value = botStats.uptime
            }
            field {
                name = "Ping"
                value = botStats.ping
            }
        }
    }
}