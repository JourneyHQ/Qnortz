package dev.yuua.qnortz.applications.commands.events

import dev.kord.common.entity.ChannelType
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.behavior.reply
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Member
import dev.kord.core.entity.Message
import dev.kord.core.entity.User
import dev.kord.core.entity.channel.Channel
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.rest.builder.message.create.MessageCreateBuilder
import dev.yuua.qnortz.Qnortz

data class TextCommandInteractionEvent(
    override val kord: Kord,
    override val qnortz: Qnortz,
    override val kordEvent: MessageCreateEvent,
    override val guild: Guild?,
    override val isFromGuild: Boolean,
    override val channel: Channel,
    override val channelType: ChannelType,
    override val member: Member?,
    override val user: User,
    val message: Message
) : CommandInteractionEventStruct<MessageCreateEvent> {
    suspend fun respondPublic(builder: MessageCreateBuilder.() -> Unit) {
        message.reply(builder)
    }

    suspend fun respondEphemeral(builder: MessageCreateBuilder.() -> Unit, toPrivateChannel: Boolean = false) {
        if (toPrivateChannel) user.getDmChannel().createMessage(builder)
        else message.reply(builder)
    }
}
