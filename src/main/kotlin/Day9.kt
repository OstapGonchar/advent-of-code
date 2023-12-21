class Day9 {

    fun part1(fileName: String): Int {
        return FileProvider().readFileAsLines(fileName)
            .map { line -> line.split(" ").map { it.toInt() } }
            .sumOf { calculateNextValue(it) }
    }

    fun part2(fileName: String): Int {
        return FileProvider().readFileAsLines(fileName)
            .map { line -> line.split(" ").map { it.toInt() } }
            .map { it.reversed() }
            .sumOf { calculateNextValue(it) }
    }

    private fun calculateNextValue(numbers: List<Int>): Int {
        return if (numbers.all { it == 0 }) {
            0
        } else {
            val differences = numbers.windowed(2, 1).map { it[1] - it[0] }
            numbers.last() + calculateNextValue(differences)
        }
    }

}