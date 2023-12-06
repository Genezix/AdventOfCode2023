package adventofcode2023.day6

import common.Program
import common.multiply

class ProgramDay6(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val regex = ".*: *(.*)".toRegex()
    private val inputs = brutInputs

    override fun part1(): String = inputs.let {
        val times = regex.find(it[0])!!.groupValues[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
        val distance = regex.find(it[1])!!.groupValues[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
        times.mapIndexed { index, time -> Race(time, distance[index]) }
    }.map { race ->
        (1..race.time).map { timePushed ->
            timePushed * (race.time - timePushed)
        }.count { it > race.distance }
    }.multiply().toString()

    override fun part2(): String = inputs.let {
        val time = regex.find(it[0])!!.groupValues[1].replace(" ", "").toLong()
        val distance = regex.find(it[1])!!.groupValues[1].replace(" ", "").toLong()
        val race = Race(time, distance)
        (1..race.time).map { timePushed ->
            timePushed * (race.time - timePushed)
        }.count { it > race.distance }
    }.toString()
}

data class Race(val time: Long, val distance: Long)
