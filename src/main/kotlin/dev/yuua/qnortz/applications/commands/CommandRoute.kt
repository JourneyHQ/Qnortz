package dev.yuua.qnortz.applications.commands

import dev.yuua.qnortz.applications.ApplicationRoute
import dev.yuua.qnortz.applications.commands.events.CommandInteractionEventStruct


/**
 * CommandRoute is a data class that represents a route for handling command interactions in an application.
 *
 * @param E The type of the command interaction event, which should be a subtype of CommandInteractionEventStruct.
 * @property subcommandGroup The group that this subcommand belongs to.
 * @property subcommand The name of the subcommand to handle.
 * @property commandCapability The capability of the command, as defined in the CommandCapability enum.
 * @property function The function to be executed when handling the command interaction event of type E.
 * @constructor Creates a new CommandRoute with the specified parameters.
 */
data class CommandRoute<E : CommandInteractionEventStruct<*>>(
    val subcommandGroup: String,
    val subcommand: String,
    val commandCapability: CommandCapability,
    val function: CommandFunction<E>
) : ApplicationRoute