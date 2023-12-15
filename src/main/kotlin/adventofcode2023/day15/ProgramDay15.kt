package adventofcode2023.day15

import common.Program

class ProgramDay15(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val inputs = brutInputs.first().split(",")

    override fun part1(): String {
        return inputs.sumOf { step ->
            var result = 0

            step.forEach {
                result += it.code
                result *= 17
                result %= 256
            }

            result
        }.toString()
    }

    override fun part2(): String {
        val regex = "([a-z]*)([-=])(.*)?".toRegex()
        val boxes = mutableMapOf<Int, List<Focal>>()
        inputs.map {
            val values = regex.find(it)!!.groupValues
            Focal(
                values[1],
                values[2].first(),
                if (values.size > 3 && values[3].isNotEmpty()) values[3].toInt() else null
            )
        }.forEach { step ->
            var boxNumber = 0

            step.label.forEach {
                boxNumber += it.code
                boxNumber *= 17
                boxNumber %= 256
            }

            if (step.operator == '-') {
                val newList = (boxes[boxNumber] ?: listOf())
                boxes[boxNumber] = newList.filterNot { it.label == step.label }
            } else {
                val newList = (boxes[boxNumber] ?: listOf())
                if (newList.any { it.label == step.label }) {
                    newList.first { it.label == step.label }.length = step.length
                } else {
                    boxes[boxNumber] = newList.plus(step)
                }
            }
        }.toString()

        return boxes.map {
            val boxNumber = it.key
            val steps = it.value
            steps.mapIndexed { index, step ->
                (boxNumber + 1) * (index + 1) * step.length!!
            }.sum()
        }.sum().toString()
    }

    private data class Focal(val label: String, val operator: Char, var length: Int? = null)
}
