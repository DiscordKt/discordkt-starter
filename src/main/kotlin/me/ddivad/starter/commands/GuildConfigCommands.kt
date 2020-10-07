package me.ddivad.starter.commands

import me.ddivad.starter.conversations.ConfigurationConversation
import me.ddivad.starter.dataclasses.Configuration
import me.ddivad.starter.services.PermissionLevel
import me.ddivad.starter.services.requiredPermissionLevel
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.RoleArg
import me.jakejmattson.discordkt.api.dsl.commands
import me.jakejmattson.discordkt.api.services.ConversationService

fun guildConfigCommands(configuration: Configuration, conversationService: ConversationService) = commands("Configuration") {
    command("configure") {
        description = "Configure a guild to use this bot."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute {
            if (configuration.hasGuildConfig(guild!!.id.longValue))
                return@execute respond("Guild configuration exists. To modify it use the commands to set values.")

            conversationService.startPublicConversation<ConfigurationConversation>(author, channel.asChannel(), guild!!)
            respond("Guild setup")
        }
    }

    command("setprefix") {
        description = "Set the bot prefix."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute(EveryArg) {
            if (!configuration.hasGuildConfig(guild!!.id.longValue))
                return@execute respond("Please run the **configure** command to set this initially.")

            val prefix = args.first
            configuration[guild!!.id.longValue]?.prefix = prefix
            configuration.save()

            respond("Prefix set to: **$prefix**")
        }
    }

    command("setstaffrole") {
        description = "Set the bot staff role."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild!!.id.longValue))
                return@execute respond("Please run the **configure** command to set this initially.")

            val role = args.first
            configuration[guild!!.id.longValue]?.staffRole = role.id.longValue
            configuration.save()

            respond("Role set to: **${role.name}**")
        }
    }

    command("setadminrole") {
        description = "Set the bot admin role."
        requiredPermissionLevel = PermissionLevel.Administrator
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild!!.id.longValue))
                return@execute respond("Please run the **configure** command to set this initially.")

            val role = args.first
            configuration[guild!!.id.longValue]?.adminRole = role.id.longValue
            configuration.save()

            respond("Role set to: **${role.name}**")
        }
    }
}