package adventofcode2023.day7

import kotlin.test.assertEquals
import org.junit.Test

internal class Day7KtTest {
    private val program = ProgramDay7(
        brutInputs = listOf(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483",
            ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "6440",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "5905",
            actual = program.part2()
        )
    }
}
