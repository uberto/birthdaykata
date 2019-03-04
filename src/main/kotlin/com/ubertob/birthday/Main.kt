package com.ubertob.birthday

import java.time.LocalDate

fun main(args: Array<String>){

    val filename = args[1]
    val today = LocalDate.now()
    val emailTemplate = EmailTemplate("Happy birthday, dear %!")
    val emailSender = EmailSender("emailServer")


    sendGreetingsToAll(filename, today, emailTemplate, emailSender)
            .forEach{println("email error $it")}
}

fun sendGreetingsToAll(filename: String, today: LocalDate, emailTemplate: EmployeeToEmail, emailSender: SendEmail) =
    FileReader(filename) { CsvRow(it).toEmployee() }
            .runReader {
                it.filter(EmployeeBirthdayFilter(today))
                        .map(emailTemplate)
                        .map(emailSender)
                        .filterNotNull()
                        .toList()
            }


