package adventofcode2023.day3

import kotlin.test.assertEquals
import org.junit.Test

internal class Day3KtTest {
    private val program = ProgramDay3(
        brutInputs = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "4361",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "467835",
            actual = program.part2()
        )
    }
}
