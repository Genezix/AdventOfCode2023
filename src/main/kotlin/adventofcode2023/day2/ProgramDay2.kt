package adventofcode2023.day2

import common.Program

class ProgramDay2(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val inputs = brutInputs.map {
        Game.build(it)
    }

    override fun part1(): String {

        val valGames = inputs.filter {
            it.cubeGrabs.all {
                it.all {
                    it.nb <= it.color.max
                }
            }
        }

        return valGames.sumOf { it.id }.toString()
    }

    override fun part2(): String {

        val multiplies = inputs.map {
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0
            it.cubeGrabs.forEach {
                it.forEach {
                    when(it.color) {
                        CubeColor.RED -> maxRed = if(maxRed < it.nb) it.nb else maxRed
                        CubeColor.GREEN -> maxGreen = if(maxGreen < it.nb) it.nb else maxGreen
                        CubeColor.BLUE -> maxBlue = if(maxBlue < it.nb) it.nb else maxBlue
                    }
                }
            }
            maxRed * maxGreen * maxBlue
        }

        return multiplies.sum().toString()
    }
}

private data class Game(val id: Int, val cubeGrabs: List<List<NbCube>>) {
    companion object {
        val idRegex = "Game ([0-9]*): .*".toRegex()
        val cubesRegex = "Game [0-9]*: (.*)".toRegex()
        fun build(input: String): Game {
            val id = idRegex.find(input)!!.groupValues[1].toInt()
            val cubeGrabs = cubesRegex.find(input)!!.groupValues[1]
            val grabs = cubeGrabs.split(";")
                .map {
                    it
                        .trim()
                        .split(",")
                        .map {
                            it
                                .trim()
                                .split(" ").let {
                                    NbCube(
                                        nb = it[0].toInt(),
                                        color = CubeColor.valueOf(it[1].uppercase())
                                    )
                                }
                        }

                }
            return Game(id, grabs)
        }
    }
}

private data class NbCube(val nb: Int, val color: CubeColor)

private enum class CubeColor(val max: Int) {
    RED(12), GREEN(13), BLUE(14);
}
