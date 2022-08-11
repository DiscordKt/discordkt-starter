package dev.discordkt.bot.commands

import dev.discordkt.bot.dataclasses.Configuration
import dev.discordkt.bot.dataclasses.GuildConfiguration
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Permissions
import me.jakejmattson.discordkt.arguments.ChannelArg
import me.jakejmattson.discordkt.commands.commands

@Suppress("unused")
fun guildConfigCommands(configuration: Configuration) = commands("Configuration", Permissions(Permission.Administrator)) {
    slash("Configure") {
        description = "Configure a guild."
        execute(ChannelArg("Logging", "Logging Channel")) {
            configuration[guild.id] = GuildConfiguration(args.first.id)
            respond("${guild.name} has been setup")
        }
    }
}