package com.gamasoft.birthday

sealed class EmailError
object ServerConnectionError: EmailError()
data class UnknownRecipient(val recipient: String): EmailError()

class EmailSender(val emailServer: String): (Email) ->  EmailError? {
    override fun invoke(email: Email): EmailError? = null //fake server

}
