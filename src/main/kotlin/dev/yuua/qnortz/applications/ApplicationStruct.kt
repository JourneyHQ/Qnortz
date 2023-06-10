package dev.yuua.qnortz.applications

import dev.yuua.qnortz.Qnortz


interface ApplicationStruct<T : ApplicationInstance<*>> {
    val qnortz: Qnortz
    val `package`: String

    val instances: List<T>

    fun initialize()
    fun submit()
    fun resolve()
}