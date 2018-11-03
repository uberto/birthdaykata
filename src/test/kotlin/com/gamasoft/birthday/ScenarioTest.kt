package com.gamasoft.birthday

import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month
import java.util.Calendar.NOVEMBER

class ScenarioTest {

    val filename = "fixtures/goodFile.csv"
    val today = LocalDate.of(2018, NOVEMBER, 1)
    val emailSender = EmailSender("server.com")
    val template = EmailTemplate("Hello Dear %!")


    @Test
    fun `happy path`(){
        sendGreetingsToAll(filename, today, template, emailSender)
    }


    @Test
    fun `csv file with errors`(){
        sendGreetingsToAll(filename, today, template, emailSender)
    }

    @Test
    fun `email server with errors`(){
        sendGreetingsToAll(filename, today, template, emailSender)
    }

}