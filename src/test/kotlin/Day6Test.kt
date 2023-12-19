import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun part1Test() {
        //given
        val expectedResult = 288

        //when
        val result = Day6().part1("day6_test.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part1Real() {
        //given
        val expectedResult = 840336

        //when
        val result = Day6().part1("day6_real.txt")

        //then
        assertEquals(expectedResult, result)
    }


    @Test
    fun part2Test() {
        //given
        val expectedResult = 71503

        //when
        val result = Day6().part2("day6_test.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part2Real() {
        //given
        val expectedResult = 41382569

        //when
        val result = Day6().part2("day6_real.txt")

        //then
        assertEquals(expectedResult, result)
    }
}