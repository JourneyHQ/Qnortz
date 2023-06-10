package dev.yuua.qnortz.applications.commands

import dev.yuua.qnortz.applications.ApplicationInstance
import dev.yuua.qnortz.applications.commands.events.CommandInteractionEventStruct

/**
 * Represents a command instance containing its name and a list of routes.
 *
 * @param E The type of the command interaction event, which should be a subtype of CommandInteractionEventStruct.
 * @property name the name of the command instance.
 * @property routes a list of command routes associated with the command instance.
 * @constructor Creates a new `CommandInstance` with specified name and list of routes.
 */
data class CommandInstance<E : CommandInteractionEventStruct<*>>(
    override val name: String,
    override val routes: List<CommandRoute<E>>
) : ApplicationInstance<CommandRoute<E>>