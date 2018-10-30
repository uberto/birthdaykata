package com.gamasoft.birthday

import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun simpleTest(){
        assert(5+5).isEqualTo(10)
    }
}