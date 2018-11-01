package com.gamasoft.birthday

class EmailTemplate(val msgTemplate: String): (Employee) -> Email {
    override fun invoke(e: Employee): Email =
            Email(e.email, "Greetings", msgTemplate.replace("%", e.firstName))

}
