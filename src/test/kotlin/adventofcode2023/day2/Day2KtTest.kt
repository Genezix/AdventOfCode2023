package adventofcode2023.day2

import kotlin.test.assertEquals
import org.junit.Test

internal class Day2KtTest {
    private val program = ProgramDay2(
        brutInputs = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "8",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "2286",
            actual = program.part2()
        )
    }
}
