package com.ubertob.birthday


sealed class  Outcome<out E: Error, out T: Any> {
    data class Success<T: Any>(val value: T): Outcome<Nothing, T>()
    data class Failure<E: Error>(val error: E): Outcome<E, Nothing>()
}

interface Error
