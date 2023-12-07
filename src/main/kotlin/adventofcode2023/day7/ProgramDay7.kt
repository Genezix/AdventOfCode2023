package adventofcode2023.day7

import common.Program

class ProgramDay7(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val inputs = brutInputs

    private fun List<String>.initHands(jValue: Int) = this.map {
        it.split(" ").let {
            val cards = it[0].map { card ->
                when (card) {
                    'T' -> 10
                    'J' -> jValue
                    'Q' -> 12
                    'K' -> 13
                    'A' -> 14
                    else -> card.toString().toInt()
                }
            }

            Hand(cards, it[1].toLong())
        }
    }

    override fun part1(): String {
        return inputs.initHands(11).sortedWith { hand1, hand2 ->
            val str1 = hand1.getStrength()
            val str2 = hand2.getStrength()
            if (str1 != str2) str1.compareTo(str2)
            else hand1.cards.zip(hand2.cards).first { it.first != it.second }.let { it.first.compareTo(it.second) }
        }.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }.sum().toString()
    }

    override fun part2(): String {
        return inputs.initHands(1).sortedWith { hand1, hand2 ->
            val str1 = hand1.getStrengthWithJoker()
            val str2 = hand2.getStrengthWithJoker()
            if (str1 != str2) str1.compareTo(str2)
            else hand1.cards.zip(hand2.cards).first { it.first != it.second }.let { it.first.compareTo(it.second) }
        }.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }.sum().toString()
    }
}

data class Hand(val cards: List<Int>, val bid: Long) {

    private val joker = 1
    private val possibleCardValues = listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14)

    fun getStrength() = cards.getStrengthCards()

    private fun List<Int>.getStrengthCards() = when {
        isFive() -> 7
        isFour() -> 6
        isFull() -> 5
        isThree() -> 4
        isTwoPair() -> 3
        isOnePair() -> 2
        else -> 1
    }

//    fun getStrengthWithJoker(): Int {
//        if (cards.none { it == joker }) return getStrength()
//
//        return cards.replaceJoker().maxOf { it.getStrengthCards() }
//    }

    fun getStrengthWithJoker(): Int {
        if (cards.none { it == joker }) return getStrength()

        return cards.replaceJoker().maxWith { cards1, cards2 ->
            val str1 = cards1.getStrengthCards()
            val str2 = cards2.getStrengthCards()
            if (str1 != str2) str1.compareTo(str2)
            else cards1.zip(cards2).first { it.first != it.second }.let { it.first.compareTo(it.second) }
        }.getStrengthCards()
    }

    private fun List<Int>.replaceJoker(): List<List<Int>> {
        if (this.contains(joker)) {
            val jokerIndex = this.indexOfFirst { it == joker }
            return possibleCardValues.flatMap { newCard ->
                this.mapIndexed { index, card -> if (index == jokerIndex) newCard else card }.replaceJoker()
            }
        }

        return listOf(this)
    }


    private fun List<Int>.isFive() = this.distinct().count() == 1
    private fun List<Int>.isFour() = this.any { card -> this.count { it == card } == 4 }
    private fun List<Int>.isFull() =
        this.distinct().let { cards -> cards.count() == 2 && this.count { it == cards[0] }.let { it == 3 || it == 2 } }

    private fun List<Int>.isThree() = this.any { card -> this.count { it == card } == 3 }
    private fun List<Int>.isTwoPair() = this.count { card -> this.count { it == card } == 2 } == 4
    private fun List<Int>.isOnePair() = this.any { card -> this.count { it == card } == 2 }
}



