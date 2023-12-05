package adventofcode2023.day4

import common.Program
import kotlin.math.pow

class ProgramDay4(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val inputs = brutInputs.map { Card.built(it) }

    override fun part1(): String =
        inputs.calculateScore().map { if (it == 0) 0 else 2.0.pow(it - 1).toLong() }.sum().toString()

    override fun part2(): String = inputs.calculateScore().map { score -> CardScore(score, 1) }.let { scores ->
        scores.apply { this.copyCards() }
    }.sumOf { it.nbCopy }.toString()

    fun List<CardScore>.copyCards() {
        this.forEachIndexed {
            index: Int, cardScore: CardScore ->
            if (cardScore.score != 0) (1..cardScore.score).forEach { this[index + it].nbCopy += cardScore.nbCopy }
            else listOf(0)
        }
    }

    fun List<Card>.calculateScore() = this.map {
        it.winningNumbers.intersect(it.numbers).size
    }
}

data class CardScore(val score: Int, var nbCopy: Int)

data class Card(val cardNumber: Long, val winningNumbers: List<Long>, val numbers: List<Long>) {
    companion object {
        private val regex = "Card (.*): (.*) \\| (.*)".toRegex()
        fun built(input: String): Card {
            val values = regex.find(input)!!.groupValues
            val cardNumber = values[1].trim().toLong()
            val winningNumbers = values[2].split(' ').filter { it != "" }.map { it.toLong() }
            val numbers = values[3].split(' ').filter { it != "" }.map { it.toLong() }
            return Card(cardNumber, winningNumbers, numbers)
        }
    }
}
