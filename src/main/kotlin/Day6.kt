class Day6 {

    fun part1(fileName: String): Int {
        val lines = FileProvider().readFileAsLines(fileName)
        val time = lines[0].substringAfter("Time:")
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
            .toList()
        val distance = lines[1].substringAfter("Distance:")
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
            .toList()
        val timeToDistance = time.zip(distance)
        return timeToDistance
            .map { calculateWinRaces(it) }
            .reduce { acc, i -> acc * i }
    }

    fun part2(fileName: String): Int {
        val lines = FileProvider().readFileAsLines(fileName)
        val time = lines[0].substringAfter("Time:")
            .replace(" ", "")
            .toLong()
        val distance = lines[1].substringAfter("Distance:")
            .replace(" ", "")
            .toLong()
        return calculateWinRaces(Pair(time, distance))
    }

    private fun calculateWinRaces(timeToDistance: Pair<Long, Long>): Int {
        return (1..<timeToDistance.first)
            .map { (timeToDistance.first - it) * it }
            .count { it > timeToDistance.second }
    }
}