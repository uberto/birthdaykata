package com.gamasoft.birthday

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun simpleTest(){
        assert(5+5).isEqualTo(10)
    }

    @Test
    fun readFile(){

        val rf = FileReader("fixtures/myfile"){CsvRow(it)}

        val lines = rf.runReader{it.toList()}

        assert(lines.count()).isEqualTo(5)
    }

    @Test
    fun readFileMap(){

        val lines = FileReader("fixtures/myfile"){CsvRow(it)}
                .runReader{ process(it) }

        assert(lines.count()).isEqualTo(4)
    }

    private fun process(it: Sequence<CsvRow>) =
            it.filter { it.row.isNotBlank() }.map { it.row }.toList()


}