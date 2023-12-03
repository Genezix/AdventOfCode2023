package adventofcode2023.day1

import common.Program

class ProgramDay1(brutInputs: List<String>, private val debug: Boolean = false) : Program {

    private val inputs = brutInputs
    private val regexNumber = "[1-9]".toRegex()
    val regexNumberLetter = ".*(one|two|three|four|five|six|seven|eight|nine|[1-9]).*".toRegex()
    val regexNumberLetterReversed = ".*(eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|[1-9]).*".toRegex()

    override fun part1(): String {
        return inputs.map {
            (it.first { regexNumber.matches(it.toString()) }.toString() +
                    it.last { regexNumber.matches(it.toString()) }.toString()).toInt()
        }.sum().toString()
    }

    override fun part2(): String {
        return inputs.map {
            (regexNumberLetterReversed.find(it.reversed())!!.groupValues[1].reversed()
                .toNum() + regexNumberLetter.find(it)!!.groupValues[1].toNum()).toInt()
        }.sum().toString()
    }
}

fun String.toNum(): String {
    return when (this) {
        "one" -> "1"
        "two" -> "2"
        "three" -> "3"
        "four" -> "4"
        "five" -> "5"
        "six" -> "6"
        "seven" -> "7"
        "eight" -> "8"
        "nine" -> "9"
        else -> this
    }
}
