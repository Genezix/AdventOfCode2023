package adventofcode2023.day8

import kotlin.test.assertEquals
import org.junit.Test

internal class Day8KtTest {
    private val program = ProgramDay8(
        brutInputs = listOf(
            "RL",
            "",
            "AAA = (BBB, CCC)",
            "BBB = (DDD, EEE)",
            "CCC = (ZZZ, GGG)",
            "DDD = (DDD, DDD)",
            "EEE = (EEE, EEE)",
            "GGG = (GGG, GGG)",
            "ZZZ = (ZZZ, ZZZ)",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "2",
            actual = program.part1()
        )
    }

    @Test
    fun part1_bis() {
        val program = ProgramDay8(
            brutInputs = listOf(
                "LLR",
                "",
                "AAA = (BBB, BBB)",
                "BBB = (AAA, ZZZ)",
                "ZZZ = (ZZZ, ZZZ)",
            ).map { it },
            debug = true
        )

        assertEquals(
            expected = "6",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        val program = ProgramDay8(
            brutInputs = listOf(
                "LR",
                "",
                "11A = (11B, XXX)",
                "11B = (XXX, 11Z)",
                "11Z = (11B, XXX)",
                "22A = (22B, XXX)",
                "22B = (22C, 22C)",
                "22C = (22Z, 22Z)",
                "22Z = (22B, 22B)",
                "XXX = (XXX, XXX)",
            ).map { it },
            debug = true
        )

        assertEquals(
            expected = "6",
            actual = program.part2()
        )
    }
}
