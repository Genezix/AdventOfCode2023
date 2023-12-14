package adventofcode2023.day13

import common.Program
import common.parseGroupsList

class ProgramDay13(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val inputs = brutInputs.parseGroupsList()

    override fun part1(): String {
        return inputs.mapNotNull { it.findMirrorValue() }.sum().toString()
    }

    override fun part2(): String {
        return inputs.sumOf { it.replaceSmudge() }.toString()
    }

    private fun List<String>.replaceSmudge(): Int {
        val existingSym = this.findMirrorValue()
        this.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, c ->
                val result = this.mapIndexed { index, line ->
                    if (index == lineIndex) {
                        line.mapIndexed { i, c ->
                            if (i == charIndex) {
                                if (c == '.') '#' else '.'
                            } else c
                        }.joinToString("")
                    } else line
                }.findMirrorValue(existingSym)

                if (result != null) {
                    return result
                }
            }
        }
        error("prout")
    }

    private fun List<String>.findMirrorByIndex(indexOne: Int, indexTwo: Int, nb: Int): Int {
        if (indexOne < 0 || indexTwo >= this.size) return nb

        if (this[indexOne] == this[indexTwo]) {
            return this.findMirrorByIndex(indexOne - 1, indexTwo + 1, nb + 1)
        }
        return 0
    }

    private fun List<String>.findMirrorValue(filter: Int? = null): Int? {
        val horizontalIndex = List(this.size) { index ->
            Pair(index, this.findMirrorByIndex(index, index + 1, 0))
        }.filter { it.second != 0 }
            .filter { filter == null || (it.first + 1) * 100 != filter }
            .maxByOrNull { it.second }?.first

        if (horizontalIndex != null) return (horizontalIndex + 1) * 100

        val vertical = this.first().mapIndexed { index, _ ->
            this.map { s -> s[index] }.joinToString(separator = "")
        }

        val verticalIndex = List(vertical.size) { index ->
            Pair(index, vertical.findMirrorByIndex(index, index + 1, 0))
        }.filter { it.second != 0 }
            .filter { filter == null || it.first + 1 != filter }
            .maxByOrNull { it.second }?.first

        if (verticalIndex != null) return verticalIndex + 1

        return null
    }
}
