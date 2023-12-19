class Day5 {

    fun part1(fileName: String): Long {
        val content = FileProvider().readFileAsString(fileName)
            .split("\n\n")
        val seeds = content[0].substringAfter("seeds: ")
            .split(" ")
            .map { it.toLong() }
            .toList()

        return lowestFromSeeds(content, seeds)
    }

    fun part2(fileName: String): Long {
        val content = FileProvider().readFileAsString(fileName)
            .split("\n\n")
        val mutableSetOfSeeds = mutableSetOf<Long>()
        val numbers = content[0].substringAfter("seeds: ")
            .split(" ")
            .map { it.toLong() }

        var min = 0L

        for (i in numbers.indices step 2) {
            println("Current index: $i")
            val seeds = (numbers[i] until numbers[i] + numbers[i + 1]).toList()
            val currMin = lowestFromSeeds(content, seeds)
            min = if (i == 0) {
                currMin
            } else {
                if (currMin < min) currMin else min
            }
            println("Current min: $min")
        }

        return min
    }

    private fun lowestFromSeeds(
        content: List<String>,
        seeds: List<Long>
    ): Long {
        val conversionMaps = content.asSequence()
            .drop(1)
            .filter { it.isNotEmpty() }
            .map { ConversionMap.parse(it) }
            .toList()

        return seeds.minOf { seed ->
            var currentValue = seed
            conversionMaps.forEach { conversionMap ->
                currentValue = conversionMap.conversions
                    .find { currentValue >= it.sourceRangeStart && currentValue < it.sourceRangeStart + it.rangeLength }
                    ?.let { it.destinationRangeStart + currentValue - it.sourceRangeStart }
                    ?: currentValue
            }
            currentValue
        }
    }

}

data class ConversionMap(val name: String, val conversions: List<Conversion>) {

    companion object {

        fun parse(text: String): ConversionMap {
            val name = text.substringBefore(":")
            val conversions = text.substringAfter(":")
                .split("\n")
                .filter { it.isNotEmpty() }
                .map { Conversion.parseLine(it) }
            return ConversionMap(name, conversions)
        }
    }
}

data class Conversion(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {

    companion object {
        fun parseLine(line: String): Conversion {
            val content = line.split(" ")
            return Conversion(content[0].toLong(), content[1].toLong(), content[2].toLong())
        }
    }
}