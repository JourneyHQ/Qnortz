package dev.yuua.qnortz

import dev.kord.core.Kord
import dev.kord.core.builder.kord.KordBuilder
import dev.kord.core.entity.Guild
import dev.kord.gateway.Intents
import dev.yuua.qnortz.Journal.Symbols.*
import dev.yuua.qnortz.applications.commands.CommandManager

/**
 * @param name The name of the Qnortz instance.
 * @param token The token for Discord bot.
 * @param intents Discord [GatewayIntent]s to enable.
 */
class Qnortz(
    val name: String,
    val token: String,
    val intents: Intents,
    qnortzBuilder: Qnortz.() -> Unit
) {
    val journal: Journal

    lateinit var kord: Kord

    // Function Managers
    private lateinit var commandManager: CommandManager
    fun enableCommands(
        functionPackage: String
    ): CommandManager {
        commandManager = CommandManager(this, functionPackage)
        return commandManager
    }

    // Development Environment
    var isDev = false
    lateinit var devPrefix: String
    private val devGuildIdList = mutableListOf<String>()
    val devGuildList = mutableListOf<Guild>()

    fun enableDevEnv(devPrefix: String, vararg devGuildIdList: String) {
        isDev = true
        this.devPrefix = devPrefix
        this.devGuildIdList.addAll(devGuildIdList)
    }

    // Builders
    suspend fun build(kordBuilder: KordBuilder.() -> Unit = {}): Qnortz {
        if (!QnortzInstances.exists(name)) {
            QnortzInstances[name] = this
        } else throw UnsupportedOperationException("$name already exists.")


        journal[Task]("Initializing $name. Please wait...")

        kord = Kord(token, kordBuilder)

        for (id in devGuildIdList) {
            val devGuild = kord.getGuildById(id)
            if (devGuild == null) {
                journal[Failure]("Cannot resolve a development guild id: $id. Skipping...")
            } else {
                devGuildList.add(devGuild)
            }
        }

        journal[Success](
            "Following guilds added as development guild :",
            *devGuildList.map { "${it.name}(${it.id})" }.toTypedArray()
        )

        if (::commandManager.isInitialized) {
            commandManager.init()
            TextCommandReactor(commandManager).script(kord)
            SlashCommandReactor(commandManager).script(kord)

            journal[Success]("Command manager initialized.")
        }

        if (::eventManager.isInitialized) {
            eventManager.init()

            journal[Success]("Event manager initialized.")
        }

        return this
    }

    fun terminate(immediate: Boolean) {
        if (immediate) kord.shutdownNow() else kord.shutdown()
        QnortzInstances.remove(name)
        journal[Info]("$name have been successfully terminated.")
    }

    init {
        this.apply(qnortzBuilder)
        journal = Journal("Qnortz/$name")
    }
}
