package adventofcode2023.day11

import common.Program
import kotlin.math.abs

class ProgramDay11(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val xList = mutableMapOf<Int, List<Galaxy>>()
    private val yList = mutableMapOf<Int, List<Galaxy>>()
    private val galaxies = brutInputs.flatMapIndexed { y: Int, line: String ->
        line.mapIndexedNotNull { x, galaxy ->
            if (galaxy == '#') Galaxy(x, y).also {
                xList[x] = (xList[x] ?: listOf()) + it
                yList[y] = (yList[y] ?: listOf()) + it
            } else null
        }
    }

    private fun Galaxy.getDistanceOf(g2: Galaxy, empty:Long): Long {
        val xs = if (this.x > g2.x) {
            (g2.x + 1 until this.x).count { !xList.containsKey(it) }
        } else {
            (this.x + 1 until g2.x).count { !xList.containsKey(it) }
        } * empty

        val ys = if (this.y > g2.y) {
            (g2.y + 1 until this.y).count { !yList.containsKey(it) }
        } else {
            (this.y + 1 until g2.y).count { !yList.containsKey(it) }
        } * empty

        return abs(this.x - g2.x) + xs + abs(this.y - g2.y) + ys
    }

    override fun part1(): String {
        return galaxies.sumOf { g1 ->
            galaxies.sumOf { g2 ->
                if (g1 == g2) 0
                else g1.getDistanceOf(g2, 1)
            }
        }.let { it / 2 }.toString()
    }

    override fun part2(): String {
        return galaxies.sumOf { g1 ->
            galaxies.sumOf { g2 ->
                if (g1 == g2) 0
                else g1.getDistanceOf(g2, 1000000L - 1)
            }
        }.let { it / 2 }.toString()
    }

    private data class Galaxy(val x: Int, val y: Int)
}
