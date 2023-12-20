import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun part1Test1() {
        //given
        val expectedResult = 2

        //when
        val result = Day8().part1("day8_test1.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part1Test2() {
        //given
        val expectedResult = 6

        //when
        val result = Day8().part1("day8_test2.txt")

        //then
        assertEquals(expectedResult, result)
    }

    @Test
    fun part1Real() {
        //given
        val expectedResult = 19241

        //when
        val result = Day8().part1("day8_real.txt")

        //then
        assertEquals(expectedResult, result)
    }
}