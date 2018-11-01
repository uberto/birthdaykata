package com.gamasoft.birthday

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class EmployeeTest {

    val file = """last_name, first_name, date_of_birth, email
Doe, John, 1982/10/08, john.doe@foobar.com
Ann, Mary, 1975/09/11, mary.ann@foobar.com""".split("\n")

    val johnDoe = CsvRow(file[1]).toEmployee()
    val maryAnn = CsvRow(file[2]).toEmployee()
    val leapYear = Employee("Leap", "Year", LocalDate.of(1992, Month.FEBRUARY, 29), "ly@foo.com")

    @Test
    fun parseRow(){
        assert(johnDoe).isEqualTo(Employee("John", "Doe", LocalDate.of(1982, Month.OCTOBER, 8), "john.doe@foobar.com"))
        assert(maryAnn).isEqualTo(Employee("Mary", "Ann", LocalDate.of(1975, Month.SEPTEMBER, 11), "mary.ann@foobar.com"))
    }

    @Test
    fun isBirthDay(){
        val johnFilter = BirthdayFilter(LocalDate.of(2018, Month.OCTOBER, 8))
        assert(johnFilter(johnDoe.dateOfBirth)).isTrue()

        val annFilter = BirthdayFilter(LocalDate.of(2011, Month.SEPTEMBER, 11))
        assert(annFilter(maryAnn.dateOfBirth)).isTrue()
    }

    @Test
    fun isNotBirthDay(){
        val todayFilter = BirthdayFilter(LocalDate.of(2018, Month.NOVEMBER, 1))

        assert(todayFilter(johnDoe.dateOfBirth)).isFalse()
        assert(todayFilter(maryAnn.dateOfBirth)).isFalse()
        assert(todayFilter(leapYear.dateOfBirth)).isFalse()
    }


    @Test
    fun isLeapYearBirthDay(){
        val notLeapYearFilter = BirthdayFilter(LocalDate.of(2018, Month.FEBRUARY, 28))
        val leapYearFilter = BirthdayFilter(LocalDate.of(2020, Month.FEBRUARY, 28))

        val actual = notLeapYearFilter(leapYear.dateOfBirth)
        assert(actual).isTrue()
        assert(leapYearFilter(leapYear.dateOfBirth)).isFalse()


    }

}

