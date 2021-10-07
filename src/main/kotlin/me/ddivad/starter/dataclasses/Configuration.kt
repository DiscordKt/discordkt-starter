package me.ddivad.starter.dataclasses

import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Role
import kotlinx.serialization.Serializable
import me.jakejmattson.discordkt.api.dsl.Data

@Serializable
data class Configuration(
        val ownerId: String = "insert-owner-id",
        var prefix: String = "++",
        val guildConfigurations: MutableMap<Long, GuildConfiguration> = mutableMapOf()
) : Data() {
    operator fun get(id: Snowflake) = guildConfigurations[id.value]
    fun hasGuildConfig(guildId: Snowflake) = guildConfigurations.containsKey(guildId.value)

    fun setup(guild: Guild, prefix: String, adminRole: Role, staffRole: Role) {
        if (guildConfigurations[guild.id.value] != null) return

        val newConfiguration = GuildConfiguration(
                guild.id.value,
                prefix,
                staffRole.id,
                adminRole.id
        )
        guildConfigurations[guild.id.value] = newConfiguration
        save()
    }
}

@Serializable
data class GuildConfiguration(
    val id: Long,
    var prefix: String = "++",
    var staffRoleId: Snowflake,
    var adminRoleId: Snowflake
)