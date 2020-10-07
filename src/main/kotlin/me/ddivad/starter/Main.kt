package me.ddivad.starter

import me.ddivad.starter.dataclasses.Configuration
import me.ddivad.starter.services.BotStatsService
import me.ddivad.starter.services.PermissionsService
import me.ddivad.starter.services.requiredPermissionLevel
import me.jakejmattson.discordkt.api.dsl.bot
import me.jakejmattson.discordkt.api.extensions.addInlineField
import java.awt.Color

suspend fun main(args: Array<String>) {
    val token = System.getenv("BOT_TOKEN") ?: null
    val prefix = System.getenv("DEFAULT_PREFIX") ?: "<none>"

    require(token != null) { "Expected the bot token as an environment variable" }

    bot(token) {
        prefix {
            val configuration = discord.getInjectionObjects(Configuration::class)

            guild?.let { configuration[it.id.longValue]?.prefix } ?: prefix
        }

        configure {
            allowMentionPrefix = true
            commandReaction = null
            theme = Color.MAGENTA
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

        permissions {
            val permissionsService = discord.getInjectionObjects(PermissionsService::class)
            val permission = command.requiredPermissionLevel
            if (guild != null)
                permissionsService.hasClearance(guild!!, user, permission)
            else
                false
        }
    }
}