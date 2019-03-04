package com.ubertob.birthday

import java.time.LocalDate
import java.time.Month

class BirthdayFilter(val today: LocalDate) : (LocalDate) -> Boolean {

    override fun invoke(dateOfBirth: LocalDate): Boolean = leapYearException(dateOfBirth) || sameDay(dateOfBirth)

    private fun sameDay(dataOfBirth: LocalDate) =
            dataOfBirth.dayOfMonth == today.dayOfMonth && dataOfBirth.month == today.month

    private fun leapYearException(dataOfBirth: LocalDate): Boolean =
            dataOfBirth.isLeapYear
            && !today.isLeapYear
            && today.month == Month.FEBRUARY
            && dataOfBirth.month == Month.FEBRUARY
            && dataOfBirth.dayOfMonth == 29
            && today.dayOfMonth == 28
}

class EmployeeBirthdayFilter(val today: LocalDate) : (Employee) -> Boolean {

    val birthdayFilter = BirthdayFilter(today)

    override fun invoke(e: Employee): Boolean  = birthdayFilter(e.dateOfBirth)

}