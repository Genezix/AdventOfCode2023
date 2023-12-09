package adventofcode2023.day9

import common.Program

class ProgramDay9(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val lines = brutInputs.map { it.split(" ").map { it.toLong() } }

    override fun part1(): String {
        return lines.sumOf {
            computeNext(it)
        }.toString()
    }

    override fun part2(): String {
        return lines.sumOf {
            computePrevious(it)
        }.toString()
    }

    private fun computePrevious(ints: List<Long>): Long {
        val intsList = computeToZero(listOf(ints))
        var addedNumber = 0L
        intsList.reversed().forEach {
            addedNumber = it.first() - addedNumber
        }
        return addedNumber
    }

    private fun computeNext(ints: List<Long>): Long {
        val intsList = computeToZero(listOf(ints))
        var addedNumber = 0L
        intsList.reversed().forEach {
            addedNumber += it.last()
        }
        return addedNumber
    }

    private fun computeToZero(ints: List<List<Long>>): List<List<Long>> {
        val lineToAdd = mutableListOf<Long>()
        val last = ints.last()
        last.forEachIndexed { index, number ->
            if (index != 0) lineToAdd.add(number - last[index - 1])
        }
        val newInts = ints.plus(listOf(lineToAdd))

        return if (lineToAdd.all { it == 0L }) {
            newInts
        } else {
            computeToZero(newInts)
        }
    }
}
