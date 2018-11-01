package com.gamasoft.birthday

import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

data class Employee(val firstName: String,
                    val lastName: String,
                    val dateOfBirth: LocalDate,
                    val email: EmailAddr)


val LOCAL_DATE = DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral('/')
                .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                .appendLiteral('/')
                .appendValue(ChronoField.DAY_OF_MONTH, 2)
                .toFormatter()


fun CsvRow.toEmployee(): Employee =
        this.row.split(",").let{
            Employee(
                firstName = it[1].trim(),
                lastName = it[0].trim(),
                email = EmailAddr(it[3].trim()),
                dateOfBirth = LocalDate.parse(it[2].trim(), LOCAL_DATE))
}
