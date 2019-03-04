package com.ubertob.birthday

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class FileReaderTest {

    @Test
    fun readFile(){

        val rf = FileReader("fixtures/wrongFile") { CsvRow(it) }

        val lines = rf.runReader{it.toList()}

        assert(lines.count()).isEqualTo(5)
    }

    @Test
    fun readFileMap(){
        fun skipBlankLines(it: Sequence<CsvRow>) =
                it.filter { it.row.isNotBlank() }.map { it.row }.toList()

        val lines = FileReader("fixtures/wrongFile") { CsvRow(it) }
                .runReader{ skipBlankLines(it) }

        assert(lines.count()).isEqualTo(4)
    }



}