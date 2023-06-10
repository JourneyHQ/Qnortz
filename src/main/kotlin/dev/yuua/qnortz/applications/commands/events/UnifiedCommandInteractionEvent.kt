package dev.yuua.qnortz.applications.commands.events

import dev.kord.common.entity.ChannelType
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.behavior.interaction.response.MessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.createEphemeralFollowup
import dev.kord.core.behavior.interaction.response.createPublicFollowup
import dev.kord.core.behavior.reply
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Member
import dev.kord.core.entity.User
import dev.kord.core.entity.channel.Channel
import dev.kord.rest.builder.message.create.MessageCreateBuilder
import dev.yuua.qnortz.Qnortz

data class UnifiedCommandInteractionEvent(
    override val kord: Kord,
    override val qnortz: Qnortz,
    override val kordEvent: CommandInteractionEvent,
    override val guild: Guild?,
    override val isFromGuild: Boolean,
    override val channel: Channel,
    override val channelType: ChannelType,
    override val member: Member?,
    override val user: User
) : CommandInteractionEventStruct<CommandInteractionEvent> {
    private var responseBehavior: MessageInteractionResponseBehavior? = null

    suspend fun respondPublic(builder: MessageCreateBuilder.() -> Unit) {
        if (kordEvent.slash != null) {
            responseBehavior?.createPublicFollowup(builder) ?: run {
                responseBehavior = kordEvent.slash!!.interaction.respondPublic(builder)
            }
        } else kordEvent.text!!.kordEvent.message.reply(builder)
    }

    suspend fun respondEphemeral(builder: MessageCreateBuilder.() -> Unit, toPrivateChannel: Boolean = false) {
        if (kordEvent.slash != null) {
            responseBehavior?.createEphemeralFollowup(builder) ?: run {
                responseBehavior = kordEvent.slash!!.interaction.respondEphemeral(builder)
            }
        } else {
            if (toPrivateChannel) kordEvent.text!!.user.getDmChannel().createMessage(builder)
            else kordEvent.text!!.message.reply(builder)
        }
    }
}
