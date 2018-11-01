package com.gamasoft.birthday

import java.time.LocalDate

fun main(args: Array<String>){

    val filename = args[1]
    val emplFilter = EmployeeBirthdayFilter(LocalDate.now())
    val emailTemplate = EmailTemplate("Happy birthday, dear %!")

    val emails = FileReader(filename){CsvRow(it)}
            .map { it.toEmployee() }
            .runReader { it.filter(emplFilter).toList() }
            .map(emailTemplate)
            .forEach { println("Sending email: $it") }



}