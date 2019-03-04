package com.ubertob.birthday

inline class EmailAddress(val emailAddress: String)

data class Email (val recipient: EmailAddress, val subject: String, val text: String)
