package com.ubertob.birthday


import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class EmployeeTest {

    val file = """last_name, first_name, date_of_birth, email
Doe, John, 1982/10/08, john.doe@foobar.com
Ann, Mary, 1975/09/11, mary.ann@foobar.com""".split("\n").drop(1).map { CsvRow(it) }

    val johnDoe = file[0].toEmployee()
    val maryAnn = file[1].toEmployee()
    val leapYear = Employee("Leap", "Year", LocalDate.of(1992, Month.FEBRUARY, 29), EmailAddress("ly@foo.com"))
    val employees = file.map { it.toEmployee() }

    @Test
    fun parseRow(){
        assertThat(johnDoe).isEqualTo(Employee("John", "Doe", LocalDate.of(1982, Month.OCTOBER, 8), EmailAddress("john.doe@foobar.com")))
        assertThat(maryAnn).isEqualTo(Employee("Mary", "Ann", LocalDate.of(1975, Month.SEPTEMBER, 11), EmailAddress("mary.ann@foobar.com")))
    }

    @Test
    fun isBirthDay(){
        val johnFilter = BirthdayFilter(LocalDate.of(2018, Month.OCTOBER, 8))
        assertThat(employees.filter { johnFilter(it.dateOfBirth) }).isEqualTo(listOf(johnDoe))
        assertThat(johnFilter(johnDoe.dateOfBirth)).isTrue()

        val annFilter = BirthdayFilter(LocalDate.of(2011, Month.SEPTEMBER, 11))
        assertThat(employees.filter { annFilter(it.dateOfBirth) }).isEqualTo(listOf(maryAnn))
        assertThat(annFilter(maryAnn.dateOfBirth)).isTrue()
    }

    @Test
    fun isNotBirthDay(){
        val todayFilter = BirthdayFilter(LocalDate.of(2018, Month.NOVEMBER, 1))
        assertThat(employees.filter { todayFilter(it.dateOfBirth) }).isEmpty()
    }


    @Test
    fun isLeapYearBirthDay(){
        val notLeapYearFilter = BirthdayFilter(LocalDate.of(2018, Month.FEBRUARY, 28))
        val leapYearFilter = BirthdayFilter(LocalDate.of(2020, Month.FEBRUARY, 28))

        assertThat(notLeapYearFilter(leapYear.dateOfBirth)).isTrue()
        assertThat(leapYearFilter(leapYear.dateOfBirth)).isFalse()
    }

}

