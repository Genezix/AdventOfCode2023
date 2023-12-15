package adventofcode2023.day10

import common.Program

class ProgramDay10(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val cells = brutInputs.flatMapIndexed { y: Int, s: String ->
        s.mapIndexedNotNull { x, c ->
            if (c != '.') Cell.build(c.toString(), x, y) else null
        }
    }
        .also { cells ->
            cells.forEach { cell ->
                val north = { cells.firstOrNull { it.y == cell.y - 1 && it.x == cell.x }.takeIf { it?.symbol in listOf("|", "F", "7") } }
                val south = { cells.firstOrNull { it.y == cell.y + 1 && it.x == cell.x }.takeIf { it?.symbol in listOf("|", "J", "L") } }
                val east = { cells.firstOrNull { it.y == cell.y && it.x == cell.x + 1 }.takeIf { it?.symbol in listOf("-", "J", "7") } }
                val west = { cells.firstOrNull { it.y == cell.y && it.x == cell.x - 1 }.takeIf { it?.symbol in listOf("-", "F", "L") } }
                when (cell.symbol) {
                    "|" -> {
                        cell.north = north.invoke()
                        cell.south = south.invoke()
                    }

                    "-" -> {
                        cell.east = east.invoke()
                        cell.west = west.invoke()
                    }

                    "F"-> {
                        cell.east = east.invoke()
                        cell.south = south.invoke()
                    }

                    "J" -> {
                        cell.north = north.invoke()
                        cell.west = west.invoke()
                    }

                    "L" -> {
                        cell.north = north.invoke()
                        cell.east = east.invoke()
                    }

                    "7" -> {
                        cell.west = west.invoke()
                        cell.south = south.invoke()
                    }

                    "S" -> {
                        cell.north = north.invoke()
                        cell.east = east.invoke()
                        cell.south = south.invoke()
                        cell.west = west.invoke()
                    }
                }
            }
        }

    override fun part1(): String {
        val start = cells.first { it.symbol == "S" }
        var previousSide1 = start
        var previousSide2 = start
        var side1 = start.sides()[0]
        var side2 = start.sides()[1]
        var counter = 1L
        while (side1 != side2) {
            counter++
            val newSide1 = side1.sides().first { it != previousSide1 }
            val newSide2 = side2.sides().first { it != previousSide2 }
            previousSide1 = side1
            previousSide2 = side2
            side1 = newSide1
            side2 = newSide2
        }
        return counter.toString()
    }

    override fun part2(): String = ""
}

private class Cell(
    val symbol: String,
    val x: Int,
    val y: Int,
    var north: Cell? = null,
    var south: Cell? = null,
    var east: Cell? = null,
    var west: Cell? = null,
) {
    fun sides() = listOfNotNull(north, east, south, west)
    companion object {
        fun build(input: String, x: Int, y: Int): Cell {
            return Cell(input, x, y)
        }
    }
}
