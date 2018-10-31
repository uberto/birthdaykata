package com.gamasoft.birthday

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun simpleTest(){
        assert(5+5).isEqualTo(10)
    }

    @Test
    fun readFile(){

        val rf = ReadFile("fixtures/myfile"){CsvRow(it)}

        val lines = rf.fold{it.toList()}

        assert(lines.count()).isEqualTo(5)
    }

    @Test
    fun readFileMap(){

        val rf = ReadFile("fixtures/myfile"){CsvRow(it)}

        val lines = rf.fold{ it.filter{ it.row.isNotBlank() }.map { it.row }.toList() }

        assert(lines.count()).isEqualTo(4)
    }


}