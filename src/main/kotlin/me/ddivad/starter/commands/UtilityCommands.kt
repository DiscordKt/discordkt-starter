package me.ddivad.starter.commands

import dev.kord.common.annotation.KordPreview
import me.ddivad.starter.dataclasses.Permissions
import me.ddivad.starter.services.HelpService
import me.jakejmattson.discordkt.api.arguments.AnyArg
import me.jakejmattson.discordkt.api.commands.commands

@KordPreview
@Suppress("unused")
fun createInformationCommands(helpService: HelpService) = commands("Utility") {
    command("help") {
        description = "Display help information."
        requiredPermission = Permissions.NONE
        execute(AnyArg("Command").optional("")) {
            val input = args.first
            if (input == "") {
                helpService.buildHelpEmbed(this)
            } else {
                val cmd = discord.commands.find { command ->
                    command.names.any { it.equals(input, ignoreCase = true) }
                } ?: return@execute
                helpService.sendHelpEmbed(this, cmd)
            }
        }
    }
}