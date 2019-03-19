package com.ubertob.birthday

import java.io.File

data class FileReader(val filename: String) {

    private val file by lazy { File(filename) }

    fun <T> runReader(f: (String) -> T ): Outcome<FileError, List<T>> =
            Outcome.tryThis{
                file.useLines {
                    it.drop(1)
                        .map(f)
                        .toList()
            }}.mapFailure { FileError(filename) }



// not needed for the exercise

//    fun <U: Any> map(g: (String) -> U): FileReader<U> = FileReader(filename, {g(f(it))})
//
//    fun <U: Any> flatmap(g: (String) -> FileReader<U>): FileReader<U> = flatten(map(g))
//
//    companion object {
//        fun <U: Any> flatten(reader: FileReader<FileReader<U>>): FileReader<U> = FileReader(reader.filename, {reader.f(it).runReader { it.first() }} )
//    }
}

data class FileError(val filename: String, val throwable: Throwable? = null): Error{
    override val msg = "file does not exists! $filename"
}
