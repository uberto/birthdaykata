package com.gamasoft.birthday

import java.time.LocalDate

fun main(args: Array<String>){
    val emplFilter = EmployeeBirthdayFilter(LocalDate.now())

    val reader = FileReader(args[1]){CsvRow(it)}

    val employees = reader
            .map { it.toEmployee() }
            .runReader { it.filter(emplFilter).toList() }

    val emailTemplate = EmailTemplate("Happy birthday, dear %!")

    val emails = employees.map(emailTemplate)

}