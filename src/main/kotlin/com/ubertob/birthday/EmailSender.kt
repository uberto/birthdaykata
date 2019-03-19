package com.ubertob.birthday

typealias SendEmail = (Email) ->  EmailAddress?


sealed class EmailError: Error

fun EmailError?.toOutcome(): Outcome<EmailError, Unit> =
        when(this) {
            null -> Success(Unit)
            else -> Failure(this)
        }

object ServerConnectionError: EmailError() {
    override val msg = "Connection Error"
}


class EmailSender(): SendEmail {
    override fun invoke(email: Email): EmailAddress? = email.recipient //fake server
}
