package dev.yuua.qnortz.applications.commands.events

data class CommandInteractionEvent(
    val slash: SlashCommandInteractionEvent? = null,
    val text: TextCommandInteractionEvent? = null
)