package com.ubertob.birthday


import java.io.File
import java.nio.charset.Charset

data class FileReader(val filename: String) {

    private val file by lazy { File(filename) }

    fun <T> runReader(f: (String) -> T ): List<T> =
            file.useLines {
                it.drop(1)
                        .map(f)
                        .toList() }



// not needed here
//    fun <U: Any> map(g: (T) -> U): FileReader<U> = FileReader(filename, {g(f(it))})
//
//    fun <U: Any> flatmap(g: (T) -> FileReader<U>): FileReader<U> = flatten(map(g))
//
//    companion object {
//        fun <U: Any> flatten(reader: FileReader<FileReader<U>>): FileReader<U> = FileReader(reader.filename, {reader.f(it).runReader { it.first() }} )
//    }
}
