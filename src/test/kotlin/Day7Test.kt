import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day7Test {
    @Test
    fun part1Test() {
        //given
        val expectedResult = 6440

        //when
        val result = Day7().part1("day7_test.txt")

        //then
        assertEquals(expectedResult, result)

    }
    @Test
    fun part1Real() {
        //given
        val expectedResult = 248836197

        //when
        val result = Day7().part1("day7_real.txt")

        //then
        assertEquals(expectedResult, result)

    }

    @ParameterizedTest
    @CsvSource(
        *arrayOf(
            "KKKKK,FIVE_OF_A_KIND",
            "QQQQA,FOUR_OF_A_KIND",
            "QQQAA,FULL_HOUSE"
        )
    )
    fun testDeckPower(card: String, deckPower: String) {
        //given
        val expectedResult = DeckPower.valueOf(deckPower)

        //when
        val result = DeckPower.getDeckPower(card)

        //then
        assertEquals(expectedResult, result)
    }
}