package com.ubertob.birthday

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class FileReaderTest {

    @Test
    fun readFile(){

        val rf = FileReader("fixtures/wrongFile")

        val lines = rf.runReader{ CsvRow(it) }

        assert(lines.count()).isEqualTo(5)
    }

    @Test
    fun readFileMap(){
        fun List<CsvRow>.skipBlankLines() =
            this.filter { it.row.isNotBlank() }.map { it.row }.toList()

        val lines = FileReader("fixtures/wrongFile")
                .runReader{ CsvRow(it) }.skipBlankLines()

        assert(lines.count()).isEqualTo(4)
    }



}