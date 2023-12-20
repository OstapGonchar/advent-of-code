class Day7 {

    fun part1(fileName: String): Int {
        val cardLabels = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
        val cardPowers = cardLabels.reversed().withIndex().associateBy({ it.value }, { it.index })
        val deckPowerFunc: (String) -> DeckPower = { DeckPower.getDeckPower(it) }

        return solve(fileName, cardPowers, deckPowerFunc)
    }

    fun part2(fileName: String): Int {
        val cardLabels = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')
        val cardPowers = cardLabels.reversed().withIndex().associateBy({ it.value }, { it.index })
        val deckPowerFunc: (String) -> DeckPower = { DeckPower.getDeckPowerWithJokers(it) }

        return solve(fileName, cardPowers, deckPowerFunc)
    }

    private fun solve(
        fileName: String,
        cardPowers: Map<Char, Int>,
        deckPowerFunc: (String) -> DeckPower
    ): Int {
        val comparator = compareBy<Deck>(
            { it.deckPower.ordinal },
            { it.cardsRank[0] },
            { it.cardsRank[1] },
            { it.cardsRank[2] },
            { it.cardsRank[3] },
            { it.cardsRank[4] },
        )
        val sortedWith = FileProvider().readFileAsLines(fileName)
            .map { Deck.toDeck(it, cardPowers, deckPowerFunc) }
            .sortedWith(comparator)
        println(sortedWith)
        return sortedWith
            .mapIndexed { i, deck -> deck.bid * (i + 1) }
            .sum()
    }

    data class Deck(val cards: String, val cardsRank: List<Int>, val deckPower: DeckPower, val bid: Int) {
        companion object {
            fun toDeck(
                line: String,
                cardPowers: Map<Char, Int>,
                getDeckPowerFunc: (cards: String) -> DeckPower
            ): Deck {
                val lineArr = line.split(" ")
                val cards = lineArr[0]
                val cardsPower = cards.toCharArray().map { cardPowers[it]!! }
                return Deck(cards, cardsPower, getDeckPowerFunc(cards), lineArr[1].toInt())
            }
        }
    }
}

enum class DeckPower {
    HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND;

    companion object {

        fun getDeckPowerWithJokers(cards: String): DeckPower {
            val baseDeckPower = getDeckPower(cards.filter { it != 'J' })
            return (1..cards.count { it == 'J' }).fold(baseDeckPower) { strength: DeckPower, _: Int ->
                when (strength) {
                    FIVE_OF_A_KIND -> FIVE_OF_A_KIND
                    FOUR_OF_A_KIND -> FIVE_OF_A_KIND
                    FULL_HOUSE -> FOUR_OF_A_KIND
                    THREE_OF_A_KIND -> FOUR_OF_A_KIND
                    TWO_PAIRS -> FULL_HOUSE
                    ONE_PAIR -> THREE_OF_A_KIND
                    HIGH_CARD -> ONE_PAIR
                }
            }
        }

        fun getDeckPower(cards: String): DeckPower {
            val values = cards.groupingBy { it }.eachCount().values
            return when {
                isFiveOfAKind(values) -> FIVE_OF_A_KIND
                isFourOfAKind(values) -> FOUR_OF_A_KIND
                isFullHouse(values) -> FULL_HOUSE
                isThreeOfAKind(values) -> THREE_OF_A_KIND
                isTwoPairs(values) -> TWO_PAIRS
                isOnePair(values) -> ONE_PAIR
                else -> HIGH_CARD
            }
        }

        private fun isFiveOfAKind(values: Collection<Int>): Boolean {
            return values.any { it == 5 }
        }

        private fun isFourOfAKind(values: Collection<Int>): Boolean {
            return values.any { it == 4 }
        }

        private fun isFullHouse(values: Collection<Int>): Boolean {
            return values.any { it == 3 } && values.any { it == 2 }
        }

        private fun isThreeOfAKind(values: Collection<Int>): Boolean {
            return values.any { it == 3 } && values.none { it == 2 }
        }

        private fun isTwoPairs(values: Collection<Int>): Boolean {
            return values.count { it == 2 } == 2
        }

        private fun isOnePair(values: Collection<Int>): Boolean {
            return values.count { it == 2 } == 1
        }
    }
}
