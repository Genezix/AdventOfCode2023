package adventofcode2023.day1

import kotlin.test.assertEquals
import org.junit.Test

internal class Day1KtTest {
    private val program = ProgramDay1(
        brutInputs = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "142",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        val program = ProgramDay1(
            brutInputs = listOf(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen",
            ).map { it },
            debug = true
        )

        assertEquals(
            expected = "281",
            actual = program.part2()
        )
    }

    @Test
    fun part3() {
        val program = ProgramDay1(
            brutInputs = listOf(
                "eightwo",
            ).map { it },
            debug = true
        )

        assertEquals(
            expected = "82",
            actual = program.part2()
        )
    }
}
