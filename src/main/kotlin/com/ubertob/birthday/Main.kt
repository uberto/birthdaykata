package com.ubertob.birthday

import com.ubertob.birthday.Outcome.*
import java.time.LocalDate

fun main(args: Array<String>){

    val filename = args[1]
    val today = LocalDate.now()
    val emailTemplate = EmailTemplate("Happy birthday, dear %!")
//    val emailTemplateReader = FileReaderFactory   extension for different templates
    val reader = FileReader(filename)

    sendGreetingsToAll(reader, today, emailTemplate, EmailSender())
            .map{ println("email sent ${it}")}
}

fun sendGreetingsToAll(
        reader: FileReader,
        today: LocalDate,
        emailTemplate: EmployeeToEmail,
        emailSender: SendEmail
) = reader.runReader { CsvRow(it).toEmployee() }
            .map {
                it.filter(EmployeeBirthdayFilter(today))
                    .map(emailTemplate)
                    .map(emailSender)
                    .filterNotNull()
                    .toList()
            }




