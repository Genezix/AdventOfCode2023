package adventofcode2023.day5

import kotlin.test.assertEquals
import org.junit.Test

internal class Day5KtTest {
    private val program = ProgramDay5(
        brutInputs = listOf(
            "seeds: 79 14 55 13",
            "",
            "seed-to-soil map:",
            "50 98 2",
            "52 50 48",
            "",
            "soil-to-fertilizer map:",
            "0 15 37",
            "37 52 2",
            "39 0 15",
            "",
            "fertilizer-to-water map:",
            "49 53 8",
            "0 11 42",
            "42 0 7",
            "57 7 4",
            "",
            "water-to-light map:",
            "88 18 7",
            "18 25 70",
            "",
            "light-to-temperature map:",
            "45 77 23",
            "81 45 19",
            "68 64 13",
            "",
            "temperature-to-humidity map:",
            "0 69 1",
            "1 0 69",
            "",
            "humidity-to-location map:",
            "60 56 37",
            "56 93 4",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "35",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "46",
            actual = program.part2()
        )
    }
}
