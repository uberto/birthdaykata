package com.ubertob.birthday

typealias SendEmail = (Email) ->  EmailError?


sealed class EmailError
object ServerConnectionError: EmailError()
data class UnknownRecipient(val recipient: String): EmailError()

class EmailSender(val emailServer: String): SendEmail {
    override fun invoke(email: Email): EmailError? = null //fake server

}
