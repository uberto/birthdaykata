package com.gamasoft.birthday

inline class EmailAddr(val emailAddr: String)

data class Email (val dest: EmailAddr, val subject: String, val text: String)
