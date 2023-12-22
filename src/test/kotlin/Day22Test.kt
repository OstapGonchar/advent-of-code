import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day22Test {

    @Test
    fun part1Test() {
        //given
        val expectedResult = 5

        //when
        val result = Day22().part1("day22_test.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part2Test() {
        //given
        val expectedResult = 7

        //when
        val result = Day22().part2("day22_test.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part1Real() {
        //given
        val expectedResult = 421

        //when
        val result = Day22().part1("day22_real.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part2Real() {
        //given
        val expectedResult = 39247

        //when
        val result = Day22().part2("day22_real.txt")

        //then
        assertEquals(expectedResult, result)
    }
}