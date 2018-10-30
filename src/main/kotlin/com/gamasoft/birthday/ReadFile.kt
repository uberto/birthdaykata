package com.gamasoft.birthday

import kotlinx.coroutines.yield
import java.io.File

data class ReadFile<T: Any>(val filename: String, val f: (String) -> T) {

    val file by lazy { File(filename) }

    fun readAll(): List<T> = file.useLines { it.map(f).toList() }

}
