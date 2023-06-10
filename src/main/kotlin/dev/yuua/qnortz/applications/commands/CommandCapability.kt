package dev.yuua.qnortz.applications.commands


/**
 * Enum class representing the command capabilities in the Qnortz application.
 *
 * @property UNIFIED Represents a command that can be invoked both with a slash command and as a textual command.
 * @property SLASH Represents a command that can only be invoked as a slash command.
 * @property TEXT Represents a command that can only be invoked as a textual command.
 */
enum class CommandCapability {
    UNIFIED, SLASH, TEXT
}