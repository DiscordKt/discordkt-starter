package me.ddivad.starter.dataclasses

import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Role
import kotlinx.serialization.Serializable
import me.jakejmattson.discordkt.dsl.Data

@Serializable
// Change the snowflake value of the ownerId below to your ID
data class Configuration(
        val ownerId: Snowflake = Snowflake(394484823944593409),
        var prefix: String = "++",
        val guildConfigurations: MutableMap<Snowflake, GuildConfiguration> = mutableMapOf()
) : Data() {
    operator fun get(id: Snowflake) = guildConfigurations[id]
    fun hasGuildConfig(guildId: Snowflake) = guildConfigurations.containsKey(guildId)

    fun setup(guild: Guild, prefix: String, adminRole: Role, staffRole: Role) {
        if (guildConfigurations[guild.id] != null) return

        val newConfiguration = GuildConfiguration(
                guild.id,
                prefix,
                staffRole.id,
                adminRole.id
        )
        guildConfigurations[guild.id] = newConfiguration
        save()
    }
}

@Serializable
data class GuildConfiguration(
    val id: Snowflake,
    var prefix: String = "++",
    var staffRoleId: Snowflake,
    var adminRoleId: Snowflake
)