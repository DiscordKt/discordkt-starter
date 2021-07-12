package me.ddivad.starter.commands

import me.ddivad.starter.conversations.ConfigurationConversation
import me.ddivad.starter.dataclasses.Configuration
import me.ddivad.starter.services.PermissionLevel
import me.ddivad.starter.services.requiredPermissionLevel
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.RoleArg
import me.jakejmattson.discordkt.api.dsl.commands

@Suppress("unused")
fun guildConfigCommands(configuration: Configuration) = commands("Configuration") {
    guildCommand("configure") {
        description = "Configure a guild to use this bot."
        requiredPermissionLevel = PermissionLevel.Administrator
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

    guildCommand("setprefix") {
        description = "Set the bot prefix."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute(EveryArg) {
            if (!configuration.hasGuildConfig(guild.id)) {
                respond("Please run the **configure** command to set this initially.")
                return@execute
            }
            val prefix = args.first
            configuration[guild.id]?.prefix = prefix
            configuration.save()
            respond("Prefix set to: **$prefix**")
        }
    }

    guildCommand("setstaffrole") {
        description = "Set the bot staff role."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild.id)) {
                respond("Please run the **configure** command to set this initially.")
                return@execute
            }
            val role = args.first
            configuration[guild.id]?.staffRoleId = role.id
            configuration.save()
            respond("Role set to: **${role.name}**")
        }
    }

    guildCommand("setadminrole") {
        description = "Set the bot admin role."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild.id)) {
                respond("Please run the **configure** command to set this initially.")
                return@execute
            }
            val role = args.first
            configuration[guild.id]?.adminRoleId = role.id
            configuration.save()
            respond("Role set to: **${role.name}**")
        }
    }
}