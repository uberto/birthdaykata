package com.ubertob.birthday

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.fail
import com.ubertob.birthday.Outcome.Failure
import com.ubertob.birthday.Outcome.Success
import org.junit.jupiter.api.Test

class FileReaderTest {

    @Test
    fun readFile(){

        val rf = FileReader("fixtures/wrongFile")

        when(val lines = rf.runReader{ CsvRow(it) }){
            is Success -> assert(lines.value.count()).isEqualTo(5)
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
            is Success -> assert(outcome.value.count()).isEqualTo(4)
            is Failure -> fail(outcome.error.msg)
        }
    }



}