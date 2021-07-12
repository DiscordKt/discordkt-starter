package me.ddivad.starter.services

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.ButtonStyle
import dev.kord.common.kColor
import dev.kord.core.entity.Guild
import dev.kord.core.entity.User
import dev.kord.x.emoji.DiscordEmoji
import dev.kord.x.emoji.Emojis
import kotlinx.coroutines.runBlocking
import me.jakejmattson.discordkt.api.annotations.Service
import me.jakejmattson.discordkt.api.arguments.ArgumentType
import me.jakejmattson.discordkt.api.arguments.OptionalArg
import me.jakejmattson.discordkt.api.dsl.Command
import me.jakejmattson.discordkt.api.dsl.CommandEvent
import me.jakejmattson.discordkt.api.dsl.Execution

@KordPreview
@Service
class HelpService(private val permissionsService: PermissionsService) {
    private suspend fun Command.isVisible(guild: Guild, user: User) =
        permissionsService.isCommandVisible(guild, user, this)

    suspend fun buildHelpEmbed(event: CommandEvent<*>) = event.respondMenu {
        val container = event.discord.commands
        fun joinNames(value: List<Command>) =
            value.joinToString("\n") { it.names.first() }

        val groupedCommands = container
            .filter { it.isVisible(event.guild!!, event.author) }
            .groupBy { it.category }
            .toList()
            .sortedByDescending { it.second.size }

        val categoryNames = container
            .filter { it.isVisible(event.guild!!, event.author) }
            .groupBy { it.category }
            .toList()
            .sortedByDescending { it.second.size }
            .map { it.first }
            .toList()

        if (groupedCommands.isNotEmpty()) {
            groupedCommands.map { (category, commands) ->
                val sorted = commands
                    .sortedBy { it.names.first() }

                page {
                    title = "Help - $category Commands"
                    description = """
                        You have **${commands.size}** commands available based on permissions.

                        Use `${event.prefix()}help` <command> for more information
                    """.trimIndent()
                    color = event.discord.configuration.theme?.kColor

                    field {
                        name = "**Commands**"
                        value = "```css\n${joinNames(sorted)}\n```"
                        inline = true
                    }
                }
            }

            categoryNames.chunked(5).forEachIndexed { index, category ->
                buttons {
                    category.forEachIndexed { page, name ->
                        button(name, getEmojiForCategory(name), ButtonStyle.Secondary) {
                            loadPage(page + index * 5)
                        }
                    }
                }
            }
        }
    }

    private fun getEmojiForCategory(categoryName: String): DiscordEmoji.Generic? {
        return when(categoryName) {
            "Configuration" -> Emojis.hammerAndPick
            "Utility" -> Emojis.toolbox
            else -> null
        }
    }

    suspend fun sendHelpEmbed(event: CommandEvent<*>, command: Command) = event.respond {
        color = event.discord.configuration.theme?.kColor
        title = command.names.joinToString(", ")
        description = command.description

        val commandInvocation = "${event.prefix()}${command.names.first()}"
        val helpBundle = command.executions.map {
            """$commandInvocation ${it.generateStructure()}
                ${
                it.arguments.joinToString("\n") { arg ->
                    """- ${arg.name}: ${arg.description} (${arg.generateExample(event)})
                    """.trimMargin()
                }
            }
            """.trimMargin()
        }
        field {
            this.value = helpBundle.joinToString("\n\n") { it }
        }
    }

    private fun ArgumentType<*>.generateExample(event: CommandEvent<*>) =
        runBlocking { generateExamples(event) }
            .takeIf { it.isNotEmpty() }
            ?.random()
            ?: "<Example>"

    private fun Execution<*>.generateStructure() = arguments.joinToString(" ") {
        val type = it.name
        if (it is OptionalArg) "[$type]" else type
    }
}