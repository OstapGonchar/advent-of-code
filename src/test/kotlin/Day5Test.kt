import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun part2Real() {
        //given
        val expectedResult = 2254686L

        //when
        val result = Day5().part2("day5_real.txt")

        //then
        assertEquals(expectedResult, result)
    }
}