package com.ubertob.birthday

import assertk.assert
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class GreetingTest {

    val template = "Happy Birthday, dear %!"


    @Test
    fun createEmail(){
        val fred = Employee("Fred", "Flintstone", LocalDate.of(-10000, Month.MAY, 1), EmailAddress("fred@flintstone.com"))

        val happyBirthdayEmailFactory = EmailTemplate(template)

        val email = happyBirthdayEmailFactory(fred)

        assertAll{
            assertThat(email.subject).isEqualTo("Greetings")
            assertThat(email.text).isEqualTo("Happy Birthday, dear Fred!")
            assertThat(email.recipient).isEqualTo(fred.email)
        }
    }
}