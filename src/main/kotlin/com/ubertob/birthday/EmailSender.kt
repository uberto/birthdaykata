package com.ubertob.birthday

import com.ubertob.birthday.Outcome.Failure
import com.ubertob.birthday.Outcome.Success

typealias SendEmail = (Email) ->  EmailError?


sealed class EmailError: Error {
    fun EmailError?.toOutcome(): Outcome<EmailError, Unit> =
            when(this) {
                null -> Success(Unit)
                else -> Failure(this)
            }
}

object ServerConnectionError: EmailError() {
    override val msg = "Connection Error"
}

data class UnknownRecipient(val recipient: EmailAddress): EmailError() {
    override val msg = "Recipient unknown $recipient"}

class EmailSender(): SendEmail {
    override fun invoke(email: Email): EmailError? = null //fake server
}
