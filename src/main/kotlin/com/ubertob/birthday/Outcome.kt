package com.ubertob.birthday

import java.lang.Exception


sealed class  Outcome<out E: Error, out T: Any> {

    data class Success<T: Any>(val value: T): Outcome<Nothing, T>()
    data class Failure<E: Error>(val error: E): Outcome<E, Nothing>()

    fun <U: Any> map(f: (T) -> U): Outcome<E, U> =
            when (this){
                is Success -> Success(f(this.value))
                is Failure -> this
            }

    fun <U: Error> mapFailure(f: (E) -> U): Outcome<U, T> =
            when (this){
                is Success -> this
                is Failure -> Failure(f(this.error))
            }



    companion object {
        fun <T: Any> tryThis(block: () -> T): Outcome<ThrowableError, T> =
                try {
                    Success(block())
                } catch (e: Throwable){
                    Failure(ThrowableError(e))
                }
    }
}

interface Error{
    val msg: String
}

data class ThrowableError(val t: Throwable): Error {
    override val msg: String
        get() = t.message.orEmpty()
}