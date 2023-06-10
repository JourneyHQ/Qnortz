package dev.yuua.qnortz.applications

interface ApplicationInstance<T> {
    val name: String
    val routes: List<T>
}