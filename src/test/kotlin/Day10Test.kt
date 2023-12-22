import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun part1Test1() {
        //given
        val expectedResult = 8

        //when
        val result = Day10().part1("day10_test1.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part1Real() {
        //given
        val expectedResult = 6800

        //when
        val result = Day10().part1("day10_real.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part2Test2() {
        //given
        val expectedResult = 4

        //when
        val result = Day10().part2("day10_test2.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part2Real() {
        //given
        val expectedResult = 483

        //when
        val result = Day10().part2("day10_real.txt")

        //then
        assertEquals(expectedResult, result)
    }
}