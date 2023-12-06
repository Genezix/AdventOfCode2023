package adventofcode2023.day5

import common.Program
import common.parseGroupsList

class ProgramDay5(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val almanac = brutInputs.parseGroupsList()

    override fun part1(): String = almanac.let { Almanac.build(it) }.lowestLocation().toString()

    override fun part2(): String = almanac.let { Almanac.buildRange(it) }.lowestLocation().toString()
}

data class Almanac(
    val seeds: List<LongRange>,
    val mapping: List<ElementMapping>
) {
    fun lowestLocation(): Long {
        return seeds.minOf {
            it.minOf { mapping.findLocation(ElementType.SEED, it) }
        }
    }

    private fun List<ElementMapping>.findLocation(source : ElementType, index: Long) : Long {
        if(source == ElementType.LOCATION) return index
        val element = this.first { it.source == source }
        val newIndex = element.mappings.firstOrNull { it.sourceRange.contains(index) }?.let {
            val delta = index - it.sourceRange.first
            it.destinationRange.first + delta
        } ?: index
        return findLocation(element.destination, newIndex)
    }

    companion object {
        fun buildRange(inputs: List<List<String>>) : Almanac {
            //seeds: 79 14 55 13
            val inputSeeds = inputs[0][0].split(": ")[1].split(" ").map { it.toLong() }
            val seeds = mutableListOf<LongRange>()
            for (i : Int in inputSeeds.indices)  {
                if(i % 2 == 0) {
                    val start = inputSeeds[i]
                    val end = start + inputSeeds[i + 1] - 1
                    seeds.add(LongRange(start, end))
                }
            }

//            seed-to-soil map:
//            50 98 2
//            52 50 48
            val elementMappings = inputs
                .filterIndexed { index, _ -> index != 0 }
                .map { mapping ->
                    mapping[0]
                        .split(" ")[0]
                        .split("-to-")
                        .map { ElementType.valueOf(it.uppercase()) }.let { elementsTypes ->
                            val mappings = mapping
                                .filterIndexed { index, _ -> index != 0 }
                                .map {
                                    it.split(" ").let { rangePart ->
                                        val size = rangePart[2].toLong() - 1
                                        val destination = rangePart[0].toLong()
                                        val source = rangePart[1].toLong()
                                        ElementRange(
                                            sourceRange = LongRange(
                                                source,
                                                source + size
                                            ),
                                            destinationRange = LongRange(
                                                destination,
                                                destination + size
                                            ),
                                        )
                                    }
                                }

                            ElementMapping(
                                elementsTypes[0],
                                elementsTypes[1],
                                mappings
                            )
                        }
                }

            return Almanac(seeds, elementMappings)
        }
        fun build(inputs: List<List<String>>): Almanac {
            //seeds: 79 14 55 13
            val seeds = inputs[0][0].split(": ")[1].split(" ").map {
                LongRange(it.toLong(), it.toLong())
            }

//            seed-to-soil map:
//            50 98 2
//            52 50 48
            val elementMappings = inputs
                .filterIndexed { index, _ -> index != 0 }
                .map { mapping ->
                    mapping[0]
                        .split(" ")[0]
                        .split("-to-")
                        .map { ElementType.valueOf(it.uppercase()) }.let { elementsTypes ->
                            val mappings = mapping
                                .filterIndexed { index, _ -> index != 0 }
                                .map {
                                    it.split(" ").let { rangePart ->
                                        val size = rangePart[2].toLong() - 1
                                        val destination = rangePart[0].toLong()
                                        val source = rangePart[1].toLong()
                                        ElementRange(
                                            sourceRange = LongRange(
                                                source,
                                                source + size
                                            ),
                                            destinationRange = LongRange(
                                                destination,
                                                destination + size
                                            ),
                                        )
                                    }
                                }

                            ElementMapping(
                                elementsTypes[0],
                                elementsTypes[1],
                                mappings
                            )
                        }
                }

            return Almanac(seeds, elementMappings)
        }
    }
}

data class ElementMapping(
    val source: ElementType,
    val destination: ElementType,
    val mappings: List<ElementRange>
)

data class ElementRange(val sourceRange: LongRange, val destinationRange: LongRange)

enum class ElementType {
    SEED, SOIL, FERTILIZER, WATER, LIGHT, TEMPERATURE, HUMIDITY, LOCATION
}
