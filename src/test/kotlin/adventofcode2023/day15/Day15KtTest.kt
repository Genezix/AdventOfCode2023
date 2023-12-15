package adventofcode2023.day15

import kotlin.test.assertEquals
import org.junit.Test

internal class Day15KtTest {
    private val program = ProgramDay15(
        brutInputs = listOf(
            "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "1320",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "145",
            actual = program.part2()
        )
    }
}
