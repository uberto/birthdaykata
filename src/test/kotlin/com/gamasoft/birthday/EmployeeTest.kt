package com.gamasoft.birthday

import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class EmployeeTest {

    val file = """last_name, first_name, date_of_birth, email
Doe, John, 1982/10/08, john.doe@foobar.com
Ann, Mary, 1975/09/11, mary.ann@foobar.com""".split("\n")

    @Test
    fun parseRow(){

        val johnDoe = CsvRow(file[1]).toEmployee()
        val maryAnn = CsvRow(file[2]).toEmployee()


        assertk.assert(johnDoe).isEqualTo(Employee("John", "Doe", LocalDate.of(1982, Month.OCTOBER, 8), "john.doe@foobar.com"))
        assertk.assert(maryAnn).isEqualTo(Employee("Mary", "Ann", LocalDate.of(1975, Month.SEPTEMBER, 11), "mary.ann@foobar.com"))
    }
}

