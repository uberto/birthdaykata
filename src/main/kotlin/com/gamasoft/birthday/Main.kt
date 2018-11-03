package com.gamasoft.birthday

import java.time.LocalDate

fun main(args: Array<String>){

    val filename = args[1]
    val emplFilter = EmployeeBirthdayFilter(LocalDate.now())
    val emailTemplate = EmailTemplate("Happy birthday, dear %!")
    val emailSender = EmailSender("emailServer")


    FileReader(filename){CsvRow(it).toEmployee()}
            .runReader { it.filter(emplFilter)
                         .map(emailTemplate)
                         .map(emailSender)
            }.filterNotNull().forEach {
                println("Sent email with error: it")
            }
}






}