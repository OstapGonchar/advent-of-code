import Day7.Companion.cardLabels

class Day7 {

    fun part1(fileName: String): Int {
        val comparator = compareBy<Deck>(
            { it.deckPower.ordinal },
            { it.cardsPower[0] },
            { it.cardsPower[1] },
            { it.cardsPower[2] },
            { it.cardsPower[3] },
            { it.cardsPower[4] },
        )
        val sortedWith = FileProvider().readFileAsLines(fileName)
            .map { Deck.toDeck(it) }
            .sortedWith(comparator)
        println(sortedWith)
        return sortedWith
            .mapIndexed { i, deck -> deck.bid * (i + 1) }
            .sum()
    }

    fun part2(fileName: String): Long {
        TODO()
    }

    data class Deck(val cards: String, val cardsPower: List<Int>, val deckPower: DeckPower, val bid: Int) {
        companion object {
            fun toDeck(line: String): Deck {
                val lineArr = line.split(" ")
                val cards = lineArr[0]
                val cardsPower = cards.toCharArray().map { cardPower[it]!! }
                return Deck(cards, cardsPower, DeckPower.getDeckPower(cards), lineArr[1].toInt())
            }
        }
    }


    companion object {
        val cardLabels = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
        val cardPower = cardLabels.reversed().withIndex().associateBy({ it.value }, { it.index })
    }
}

enum class DeckPower {
    HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND;

    companion object {
        fun getDeckPower(cards: String): DeckPower {
            val cardsMap = cardLabels.associateWith { 0 }.toMutableMap()
            cards.forEach { cardsMap[it] = cardsMap[it]!! + 1 }
            return when {
                isFiveOfAKind(cardsMap) -> FIVE_OF_A_KIND
                isFourOfAKind(cardsMap) -> FOUR_OF_A_KIND
                isFullHouse(cardsMap) -> FULL_HOUSE
                isThreeOfAKind(cardsMap) -> THREE_OF_A_KIND
                isTwoPairs(cardsMap) -> TWO_PAIRS
                isOnePair(cardsMap) -> ONE_PAIR
                else -> HIGH_CARD
            }
        }

        private fun isFiveOfAKind(cardsMap: Map<Char, Int>): Boolean {
            return cardsMap.values.any { it == 5 }
        }

        private fun isFourOfAKind(cardsMap: Map<Char, Int>): Boolean {
            return cardsMap.values.any { it == 4 }
        }

        private fun isFullHouse(cardsMap: Map<Char, Int>): Boolean {
            return cardsMap.values.any { it == 3 } && cardsMap.values.any { it == 2 }
        }

        private fun isThreeOfAKind(cardsMap: Map<Char, Int>): Boolean {
            return cardsMap.values.any { it == 3 } && cardsMap.values.none { it == 2 }
        }

        private fun isTwoPairs(cardsMap: Map<Char, Int>): Boolean {
            return cardsMap.values.count { it == 2 } == 2
        }

        private fun isOnePair(cardsMap: Map<Char, Int>): Boolean {
            return cardsMap.values.count { it == 2 } == 1
        }
    }
}
