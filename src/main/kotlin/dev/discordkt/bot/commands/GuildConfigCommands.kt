package dev.discordkt.bot.commands

import dev.discordkt.bot.data.Configuration
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Permissions
import me.jakejmattson.discordkt.commands.commands

@Suppress("unused")
fun guildConfigCommands(configuration: Configuration) = commands("Configuration", Permissions(Permission.Administrator)) {
    slash("configure") {
        description = "Configure a guild to use this bot."
        execute {
            if (configuration.hasGuildConfig(guild.id)) {
                respond("Guild configuration exists. To modify it use the commands to set values.")
                return@execute
            }
            ConfigurationConversation(configuration)
                .createConfigurationConversation(guild)
                .startPublicly(discord, author, channel)
            respond("${guild.name} setup")
        }
    }
}