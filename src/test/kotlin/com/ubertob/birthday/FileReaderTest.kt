package com.ubertob.birthday

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.fail
import org.junit.jupiter.api.Test

class FileReaderTest {

    @Test
    fun readFile(){

        val rf = FileReader("fixtures/wrongFile")

        when(val lines = rf.runReader{ CsvRow(it) }){
            is Success -> assertThat(lines.value.count()).isEqualTo(5)
            is Failure -> fail(lines.error.msg)
        }

    }

    @Test
    fun readFileMap(){
        fun List<CsvRow>.skipBlankLines() =
            this.filter { it.row.isNotBlank() }.map { it.row }.toList()

        val outcome = FileReader("fixtures/wrongFile")
                .runReader{ CsvRow(it) }.map {it.skipBlankLines()}

        when(outcome){
            is Success -> assertThat(outcome.value.count()).isEqualTo(4)
            is Failure -> fail(outcome.error.msg)
        }
    }



}