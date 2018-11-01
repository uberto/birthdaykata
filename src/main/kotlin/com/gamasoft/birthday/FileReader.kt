package com.gamasoft.birthday


import java.io.File

data class FileReader<T: Any>(val filename: String, val f: (String) -> T) {

    private val file by lazy { File(filename) }

    fun <U> runReader(g: (Sequence<T>) -> U ): U = file.useLines { g(it.map(f)) }

    fun <U: Any> map(g: (T) -> U): FileReader<U> = FileReader(filename, {g(f(it))})

    fun <U: Any> flatmap(g: (T) -> FileReader<U>): FileReader<U> = flatten(map(g))

    companion object {
        fun <U: Any> flatten(reader: FileReader<FileReader<U>>): FileReader<U> = FileReader(reader.filename, {reader.f(it).runReader { it.first() }} )
    }
}
