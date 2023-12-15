package adventofcode2023.day10

import kotlin.test.assertEquals
import org.junit.Test

internal class Day10KtTest {
    private val program = ProgramDay10(
        brutInputs = listOf(
            ".....",
            ".S-7.",
            ".|.|.",
            ".L-J.",
            ".....",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "4",
            actual = program.part1()
        )
    }

    @Test
    fun part1_bis() {
        val program = ProgramDay10(
            brutInputs = listOf(
                "..F7.",
                ".FJ|.",
                "SJ.L7",
                "|F--J",
                "LJ...",
            ).map { it },
            debug = true
        )

        assertEquals(
            expected = "8",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "",
            actual = program.part2()
        )
    }
}
