package dev.yuua.qnortz.applications.commands.builders

import dev.yuua.qnortz.applications.commands.CommandCapability
import dev.yuua.qnortz.applications.commands.CommandFunction
import dev.yuua.qnortz.applications.commands.events.CommandInteractionEventStruct
import dev.yuua.qnortz.applications.commands.events.SlashCommandInteractionEvent
import dev.yuua.qnortz.applications.commands.events.TextCommandInteractionEvent
import dev.yuua.qnortz.applications.commands.events.UnifiedCommandInteractionEvent

/**
 * Represents a command in the application.
 *
 * @param E The type of the command interaction event, which should be a subtype of CommandInteractionEventStruct.
 * @param name The unique name of the command.
 * @param description A brief description of the command.
 * @param capability The type of command capability (e.g., Slash, Text, or Unified).
 * @param builder A lambda with receiver for configuring the command.
 */
class Command<E : CommandInteractionEventStruct<*>>(
    val name: String,
    val description: String,
    val capability: CommandCapability,
    builder: Command<E>.() -> Unit
) {
    companion object {
        /**
         * Creates a unified command.
         *
         * @param name The unique name of the command.
         * @param description The description of the command.
         * @param builder A lambda with receiver for configuring the unified command.
         * @return A unified command with the specified attributes.
         */
        fun unified(name: String, description: String, builder: Command<UnifiedCommandInteractionEvent>.() -> Unit) =
            Command(name, description, CommandCapability.UNIFIED, builder)

        
        /**
         * Creates a slash command.
         *
         * @param name The unique name of the slash command.
         * @param description The description of the slash command.
         * @param builder A lambda with receiver for configuring the slash command.
         * @return A slash command with the specified attributes.
         */
        fun slash(name: String, description: String, builder: Command<SlashCommandInteractionEvent>.() -> Unit) =
            Command(name, description, CommandCapability.SLASH, builder)

        
        /**
         * Creates a text command.
         *
         * @param name The unique name of the text command.
         * @param description The description of the text command.
         * @param builder A lambda with receiver for configuring the text command.
         * @return A text command with the specified attributes.
         */
        fun text(name: String, description: String, builder: Command<TextCommandInteractionEvent>.() -> Unit) =
            Command(name, description, CommandCapability.TEXT, builder)
    }

    var commandFunction: CommandFunction<E>? = null

    fun function(
        route: String,
        precheck: suspend E.() -> Boolean = { true },
        function: suspend E.() -> Unit
    ) {
        this.commandFunction = CommandFunction(precheck, function)
    }

    init {
        this.apply(builder)
    }
}

