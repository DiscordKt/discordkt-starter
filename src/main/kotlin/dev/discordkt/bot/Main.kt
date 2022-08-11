package dev.discordkt.bot

import dev.discordkt.bot.dataclasses.Configuration
import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Permissions
import dev.kord.gateway.PrivilegedIntent
import me.jakejmattson.discordkt.dsl.bot
import java.awt.Color

@KordPreview
@PrivilegedIntent
fun main() {
    val token = System.getenv("BOT_TOKEN") ?: null

    bot(token) {
        val configuration = data("data/config/config.json") { Configuration() }

        prefix { "/" }

        configure {
            mentionAsPrefix = true
            commandReaction = null
            theme = Color.MAGENTA
            defaultPermissions = Permissions(Permission.UseApplicationCommands)
        }
    }
}