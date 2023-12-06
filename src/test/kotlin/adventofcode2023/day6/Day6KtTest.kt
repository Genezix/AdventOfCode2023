package adventofcode2023.day6

import kotlin.test.assertEquals
import org.junit.Test

internal class Day6KtTest {
    private val program = ProgramDay6(
        brutInputs = listOf(
            "Time:      7  15   30",
            "Distance:  9  40  200",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "288",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "71503",
            actual = program.part2()
        )
    }
}
