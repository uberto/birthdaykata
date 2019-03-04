package com.ubertob.birthday

typealias EmployeeToEmail = (Employee) -> Email

class EmailTemplate(val msgTemplate: String): EmployeeToEmail {
    override fun invoke(e: Employee): Email =
            Email(e.email, "Greetings", msgTemplate.replace("%", e.firstName))

}
