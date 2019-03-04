package com.ubertob.birthday

import java.time.LocalDate

fun main(args: Array<String>){

    val filename = args[1]
    val today = LocalDate.now()
    val emailTemplate = EmailTemplate("Happy birthday, dear %!")
    val emailSender = EmailSender("emailServer")
    val reader = FileReader(filename) { CsvRow(it).toEmployee() }

    sendGreetingsToAll(reader, today, emailTemplate, emailSender)
            .forEach{println("email error $it")}
}

fun sendGreetingsToAll(reader: FileReader<Employee>, today: LocalDate, emailTemplate: EmployeeToEmail, emailSender: SendEmail) =

    reader.runReader {
        it.filter(EmployeeBirthdayFilter(today))
                .map(emailTemplate)
                .map(emailSender)
                .filterNotNull()
                .toList()
    }


