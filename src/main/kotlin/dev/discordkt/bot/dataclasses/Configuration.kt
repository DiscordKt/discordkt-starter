package dev.discordkt.bot.dataclasses

import dev.kord.common.entity.Snowflake
import kotlinx.serialization.Serializable
import me.jakejmattson.discordkt.dsl.Data

@Serializable
data class Configuration(val guildConfigurations: MutableMap<Snowflake, GuildConfiguration> = mutableMapOf()) : Data() {
    operator fun get(id: Snowflake) = guildConfigurations[id]

    operator fun set(id: Snowflake, configuration: GuildConfiguration) {
        guildConfigurations[id] = configuration
        save()
    }

    fun hasGuildConfig(guildId: Snowflake) = guildConfigurations.containsKey(guildId)
}

@Serializable
data class GuildConfiguration(
    var channel: Snowflake
)