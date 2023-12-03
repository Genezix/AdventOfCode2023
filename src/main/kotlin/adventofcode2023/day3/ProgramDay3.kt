package adventofcode2023.day3

import common.Program
import common.multiply

class ProgramDay3(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val inputs = brutInputs
    override fun part1(): String {
        val partsGrid = PartsGrid.build(inputs)
        var sum = 0
        partsGrid.symbols.forEach {
            val neighbors = mutableListOf<List<Part>>()
            it.neighbors.forEach { part ->
                if (neighbors.none { it.contains(part) }) {
                    neighbors.add(part.findLeftRightNeighbors(listOf(part)).sortedBy { it.x })
                }
            }
            sum += neighbors.map { it.distinct().map { it.value }.joinToString(separator = "").toInt() }.sum()
        }
        return sum.toString()
    }

    override fun part2(): String {
        val partsGrid = PartsGrid.build(inputs)
        var sum = 0
        partsGrid.symbols.filter { it.value == '*' }.forEach {
            val neighbors = mutableListOf<List<Part>>()
            it.neighbors.forEach { part ->
                if (neighbors.none { it.contains(part) }) {
                    neighbors.add(part.findLeftRightNeighbors(listOf(part)).sortedBy { it.x })
                }
            }

            if (neighbors.size > 1)
                sum += neighbors.map { it.distinct().map { it.value }.joinToString(separator = "").toInt() }.multiply()
        }
        return sum.toString()
    }
}

private data class PartsGrid(val symbols: List<Part>, val parts: List<Part>) {
    companion object {
        private val numberRegex = "[0-9]".toRegex()
        fun build(inputs: List<String>): PartsGrid {
            val symbolParts = mutableListOf<Part>()
            val parts = mutableListOf<Part>()
            inputs.forEachIndexed { y: Int, s: String ->
                s.forEachIndexed { x, value ->
                    val part = Part(x = x, y = y, value = value).takeIf { it.value != '.' }
                    if (part != null && numberRegex.matches(part.value.toString())) parts.add(part)
                    if (part != null && !numberRegex.matches(part.value.toString())) symbolParts.add(part)
                }
            }
            parts.forEach { part ->
                part.findAndSetNeighbors(parts)
            }
            symbolParts.forEach { part ->
                part.findAndSetNeighbors(parts)
            }

            return PartsGrid(symbols = symbolParts, parts = parts)
        }
    }
}

private data class Part(val x: Int, val y: Int, val value: Char, var neighbors: List<Part> = emptyList()) {
    fun findAndSetNeighbors(parts: List<Part>) {
        neighbors = listOfNotNull(
            parts.firstOrNull { it.x == x - 1 && it.y == y - 1 },
            parts.firstOrNull { it.x == x - 1 && it.y == y },
            parts.firstOrNull { it.x == x - 1 && it.y == y + 1 },
            parts.firstOrNull { it.x == x && it.y == y - 1 },
            parts.firstOrNull { it.x == x && it.y == y + 1 },
            parts.firstOrNull { it.x == x + 1 && it.y == y - 1 },
            parts.firstOrNull { it.x == x + 1 && it.y == y },
            parts.firstOrNull { it.x == x + 1 && it.y == y + 1 },
        )
    }

    fun findLeftRightNeighbors(currentList: List<Part> = emptyList()): List<Part> {
        val newList = listOfNotNull(
            neighbors.firstOrNull { it.x == x - 1 && it.y == y && !currentList.contains(it) },
            neighbors.firstOrNull { it.x == x + 1 && it.y == y && !currentList.contains(it) },
        )

        if (newList.isEmpty()) return currentList

        val result = newList.flatMap {
            it.findLeftRightNeighbors(newList.plus(currentList))
        }

        return result.plus(currentList)
    }


    override fun toString(): String {
        return "Part(x=$x, y=$y, value=$value)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Part

        if (x != other.x) return false
        if (y != other.y) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + value.hashCode()
        return result
    }
}
