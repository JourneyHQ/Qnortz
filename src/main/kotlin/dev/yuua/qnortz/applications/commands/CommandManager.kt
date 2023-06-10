package dev.yuua.qnortz.applications.commands

import dev.yuua.qnortz.Qnortz
import dev.yuua.qnortz.applications.ApplicationStruct

class CommandManager(
    override val qnortz: Qnortz,
    override val `package`: String
) : ApplicationStruct<CommandInstance<*>> {
    override val instances: List<CommandInstance<*>> = emptyList()

    override fun initialize() {

    }

    override fun submit() {

    }

    override fun resolve() {

    }
}