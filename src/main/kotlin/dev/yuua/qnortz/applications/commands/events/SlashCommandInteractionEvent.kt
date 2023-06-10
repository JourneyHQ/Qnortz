package dev.yuua.qnortz.applications.commands.events

import dev.kord.common.entity.ChannelType
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.behavior.interaction.response.MessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.createEphemeralFollowup
import dev.kord.core.behavior.interaction.response.createPublicFollowup
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Member
import dev.kord.core.entity.User
import dev.kord.core.entity.channel.Channel
import dev.kord.core.entity.interaction.ChatInputCommandInteraction
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.message.create.MessageCreateBuilder
import dev.yuua.qnortz.Qnortz

data class SlashCommandInteractionEvent(
    override val kord: Kord,
    override val qnortz: Qnortz,
    override val kordEvent: ChatInputCommandInteractionCreateEvent,
    override val guild: Guild?,
    override val isFromGuild: Boolean,
    override val channel: Channel,
    override val channelType: ChannelType,
    override val member: Member?,
    override val user: User,
    val interaction: ChatInputCommandInteraction
) : CommandInteractionEventStruct<ChatInputCommandInteractionCreateEvent> {
    private var responseBehavior: MessageInteractionResponseBehavior? = null

    suspend fun respondPublic(builder: MessageCreateBuilder.() -> Unit) {
        responseBehavior?.createPublicFollowup(builder) ?: run {
            responseBehavior = kordEvent.interaction.respondPublic(builder)
        }
    }

    suspend fun respondEphemeral(builder: MessageCreateBuilder.() -> Unit) {
        responseBehavior?.createEphemeralFollowup(builder) ?: run {
            responseBehavior = kordEvent.interaction.respondEphemeral(builder)
        }
    }

    // todo defer
}
