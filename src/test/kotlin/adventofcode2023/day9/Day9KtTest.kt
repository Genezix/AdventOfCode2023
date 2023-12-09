package adventofcode2023.day9

import kotlin.test.assertEquals
import org.junit.Test

internal class Day9KtTest {
    private val program = ProgramDay9(
        brutInputs = listOf(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "114",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "2",
            actual = program.part2()
        )
    }
}
