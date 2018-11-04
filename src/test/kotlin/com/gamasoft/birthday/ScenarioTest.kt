package com.gamasoft.birthday

import assertk.assert
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class ScenarioTest {

    val sentEmails = mutableListOf<Email>()
    val filename = "fixtures/bigFile.csv"
    val today = LocalDate.of(2018, Month.NOVEMBER, 1)

    val emailSenderOk: SendEmail = {
        sentEmails.add(it)
        null
    }

    val emailSenderNotOk: SendEmail = {
        ServerConnectionError
    }

    val template = EmailTemplate("Hello Dear %!")


    @Test
    fun `happy path`(){
        val r = sendGreetingsToAll(filename, today, template, emailSenderOk)

        assert (r ).isEmpty()
        assert(sentEmails).hasSize(2)
    }


//    @Test
//    fun `csv file with errors`(){
//        sendGreetingsToAll(filename, today, template, emailSender)
//    }

    @Test
    fun `email server with errors`(){
        val r = sendGreetingsToAll(filename, today, template, emailSenderNotOk)

        assert (r ).hasSize(2)

        assert(sentEmails).hasSize(0)
    }

}