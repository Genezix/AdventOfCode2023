package adventofcode2023.day11

import kotlin.test.assertEquals
import org.junit.Test

internal class Day11KtTest {
    private val program = ProgramDay11(
        brutInputs = listOf(
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#.....",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "374",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "8410",
            actual = program.part2()
        )
    }
}
