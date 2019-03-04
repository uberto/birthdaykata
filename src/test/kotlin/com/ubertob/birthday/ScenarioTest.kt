package com.ubertob.birthday

import assertk.assert
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class ScenarioTest {

    val sentEmails = mutableListOf<Email>()
    val today = LocalDate.of(2018, Month.NOVEMBER, 1)

    val emailSenderOk: SendEmail = {
        sentEmails.add(it)
        null
    }

    val emailSenderNotOk: SendEmail = {
        ServerConnectionError
    }

    val template = EmailTemplate("Hello Dear %!")

    val filename = "fixtures/bigFile.csv"
    val reader = FileReader(filename)


    @Test
    fun `happy path`(){
        val r = sendGreetingsToAll(reader, today, template, emailSenderOk)

        assert (r ).isEmpty()
        assert(sentEmails).hasSize(2)
    }


//    @Test
//    fun `csv file with errors`(){
//        sendGreetingsToAll(reader, today, template, emailSender)
//    }

    @Test
    fun `email server with errors`(){
        val r = sendGreetingsToAll(reader, today, template, emailSenderNotOk)

        assert (r ).hasSize(2)

        assert(sentEmails).hasSize(0)
    }

}