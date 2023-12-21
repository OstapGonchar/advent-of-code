import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun part1Test() {
        //given
        val expectedResult = 114

        //when
        val result = Day9().part1("day9_test.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part2Test() {
        //given
        val expectedResult = 2

        //when
        val result = Day9().part2("day9_test.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part1Real() {
        //given
        val expectedResult = 1743490457

        //when
        val result = Day9().part1("day9_real.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part2Real() {
        //given
        val expectedResult = 1053

        //when
        val result = Day9().part2("day9_real.txt")

        //then
        assertEquals(expectedResult, result)

    }
}