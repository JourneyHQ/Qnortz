package dev.yuua.qnortz.applications.commands.events

import dev.kord.common.entity.ChannelType
import dev.kord.core.Kord
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Member
import dev.kord.core.entity.User
import dev.kord.core.entity.channel.Channel
import dev.yuua.qnortz.Qnortz

interface CommandInteractionEventStruct<T> {
    val kord: Kord
    val qnortz: Qnortz

    val kordEvent: T

    val guild: Guild?
    val isFromGuild: Boolean

    val channel: Channel
    val channelType: ChannelType

    val member: Member?
    val user: User
}