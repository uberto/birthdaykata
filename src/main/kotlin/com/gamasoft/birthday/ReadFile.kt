package com.gamasoft.birthday


import java.io.File

data class ReadFile<T: Any>(val filename: String, val f: (String) -> T) {

    private val file by lazy { File(filename) }

    fun <U> fold(g: (Sequence<T>) -> U ): U = file.useLines { g(it.map(f)) }

    fun <U: Any> map(g: (T) -> U): ReadFile<U> = ReadFile(filename, {g(f(it))})

    fun <U: Any> flatmap(g: (T) -> ReadFile<U>): ReadFile<U> = TODO()


}
