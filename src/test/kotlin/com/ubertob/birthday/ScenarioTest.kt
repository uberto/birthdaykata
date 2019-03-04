package com.ubertob.birthday

import assertk.assert
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
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

        assert(r).isInstanceOf(Outcome.Success::class.java)
        assert ( (r as Outcome.Success).value).isEmpty()
        assert(sentEmails).hasSize(2)
    }


    @Test
    fun `csv file with errors`(){
        val r = sendGreetingsToAll(FileReader("NoFile"), today, template, emailSenderOk)

        assert(r).isInstanceOf(Outcome.Failure::class.java)
        assert ( (r as Outcome.Failure).error.msg)
                .isEqualTo("file does not exists! NoFile")

    }

    @Test
    fun `email server with errors`(){
        val r = sendGreetingsToAll(reader, today, template, emailSenderNotOk)

        assert(r).isInstanceOf(Outcome.Success::class.java)
        assert ((r as Outcome.Success).value ).hasSize(2)

        assert(sentEmails).hasSize(0)
    }

}