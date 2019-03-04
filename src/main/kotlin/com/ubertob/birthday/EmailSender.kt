package com.ubertob.birthday

typealias SendEmail = (Email) ->  EmailError?


sealed class EmailError: Error
object ServerConnectionError: EmailError()
data class UnknownRecipient(val recipient: EmailAddress): EmailError()

class EmailSender(): SendEmail {
    override fun invoke(email: Email): EmailError? = null //fake server
}
