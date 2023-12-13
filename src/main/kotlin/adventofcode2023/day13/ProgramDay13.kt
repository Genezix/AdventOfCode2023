package adventofcode2023.day13

import common.Program
import common.parseGroupsList

class ProgramDay13(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val inputs = brutInputs.parseGroupsList()

    override fun part1(): String {
        return inputs.sumOf { it.findMirrorValue() }.toString()
    }

    private fun List<String>.findMirrorValue(): Int {
        val horizontalStart = this
            .indexOfLast { it == this.first() }
            .takeIf { index -> this.count { this[index] == it } >= 2 }
            // TODO vérifier que ce qu'il y a au milieu est bien symetrique
            ?.let { index ->
                ((index + 1) / 2)
            }

        val horizontalLast = this
            .indexOfFirst { it == this.last() }
            .takeIf { index -> this.count { this[index] == it } >= 2 }
            ?.let { index ->
                ((this.size - index) / 2) + 1
            }

        val vertical = this.first().mapIndexed { index, _ ->
            this.map { s -> s[index] }.joinToString(separator = "")
        }

        val verticalStart = vertical
            .indexOfLast { it == vertical.first() }
            .takeIf { index -> vertical.count { vertical[index] == it } >= 2 }
            ?.let { index ->
                ((index + 1) / 2)
            }

        val verticalLast = vertical
            .indexOfFirst { it == vertical.last() }
            .takeIf { index -> vertical.count { vertical[index] == it } >= 2 }
            ?.let { index ->
                (index + (vertical.size - index) / 2) + 1
            }

        return listOfNotNull(
            horizontalStart?.let { it * 100 },
            horizontalLast?.let { it * 100 },
            verticalStart,
            verticalLast,
        ).max()

        // 32946 to low
        // 34146 to low
        // 34236 to low
        // 44806 ??
    }

    override fun part2(): String = ""

    private data class LineByIndex(val index: Int, val line: String)
}
