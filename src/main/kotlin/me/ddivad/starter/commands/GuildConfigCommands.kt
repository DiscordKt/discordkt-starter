package me.ddivad.starter.commands

import me.ddivad.starter.conversations.ConfigurationConversation
import me.ddivad.starter.dataclasses.Configuration
import me.ddivad.starter.dataclasses.Permissions
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.RoleArg
import me.jakejmattson.discordkt.api.commands.commands

@Suppress("unused")
fun guildConfigCommands(configuration: Configuration) = commands("Configuration") {
    command("setup") {
        description = "Configure a guild to use this bot."
        requiredPermission = Permissions.ADMINISTRATOR
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

    command("setprefix") {
        description = "Set the bot prefix."
        requiredPermission = Permissions.ADMINISTRATOR
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

    command("setstaffrole") {
        description = "Set the bot staff role."
        requiredPermission = Permissions.ADMINISTRATOR
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

    command("setadminrole") {
        description = "Set the bot admin role."
        requiredPermission = Permissions.ADMINISTRATOR
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