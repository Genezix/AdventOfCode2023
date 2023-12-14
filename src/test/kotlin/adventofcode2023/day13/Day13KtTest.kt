package adventofcode2023.day13

import kotlin.test.assertEquals
import org.junit.Test

internal class Day13KtTest {
    private val program = ProgramDay13(
        brutInputs = listOf(
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#.",
            "",
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "405",
            actual = program.part1()
        )
    }

    @Test
    fun part1_bis() {
        val program = ProgramDay13(
            brutInputs = listOf(
                "#..###..#####",
                "###..#.......",
                ".##.#.#.###..",
                "#####....####",
                "#####....####",
                ".##.#.#.###..",
                "###..#.......",
                "#..###..#####",
                "#.##.##.....#",
                "#########.###",
                "#.##.#..#.###",
                "#.##.#..#.###",
                "#########.###",
                "#.##.##.....#",
                "#.####..#####",
                "###..#.......",
                ".##.#.#.###..",
            ).map { it },
            debug = true
        )

        assertEquals(
            expected = "400",
            actual = program.part1()
        )
    }

    @Test
    fun part1_ter() {
        val program = ProgramDay13(
            brutInputs = listOf(
                ".#.######.#....",
                "##.#....#.##..#",
                "#.##.##.##.####",
                "#.#...#..#.####",
                "############..#",
                "...#.##.#......",
                "...#....#......",
                "###.#..#.###..#",
                "..########..##.",
                ".##########....",
                ".#.##..##.#.##.",
            ).map { it },
            debug = true
        )

        assertEquals(
            expected = "13",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "400",
            actual = program.part2()
        )
    }
}
