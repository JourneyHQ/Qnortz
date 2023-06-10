package dev.yuua.qnortz.applications.commands

import dev.yuua.qnortz.applications.commands.builders.Option
import dev.yuua.qnortz.applications.commands.events.CommandInteractionEventStruct
import dev.yuua.qnortz.applications.commands.events.SlashCommandInteractionEvent
import dev.yuua.qnortz.applications.commands.events.TextCommandInteractionEvent
import dev.yuua.qnortz.applications.commands.events.UnifiedCommandInteractionEvent

typealias UnifiedCommandFunction = CommandFunction<UnifiedCommandInteractionEvent>
typealias TextCommandFunction = CommandFunction<TextCommandInteractionEvent>
typealias SlashCommandFunction = CommandFunction<SlashCommandInteractionEvent>

data class CommandFunction<T : CommandInteractionEventStruct<*>>(
    val options: List<Option>,
    val precheck: suspend T.() -> Boolean,
    val function: suspend T.() -> Unit
)
