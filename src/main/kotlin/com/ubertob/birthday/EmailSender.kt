package com.ubertob.birthday

typealias SendEmail = (Email) ->  EmailError?


sealed class EmailError: Error
object ServerConnectionError: EmailError() {
    override val msg = "Connection Error"
}

data class UnknownRecipient(val recipient: EmailAddress): EmailError() {
    override val msg = "Recipient unknown $recipient"}

class EmailSender(): SendEmail {
    override fun invoke(email: Email): EmailError? = null //fake server
}
