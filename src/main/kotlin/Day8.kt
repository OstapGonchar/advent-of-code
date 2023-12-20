class Day8 {

    fun part1(fileName: String): Int {
        val lines = FileProvider().readFileAsLines(fileName)

        val instructions = lines[0]

        val network = lines
            .drop(2)
            .associate { toNode(it) }

        var currentNode = "AAA"
        var instructionsExecuted = 0
        var instructionIndex = 0
        val instructionLimit = instructions.length

        while (currentNode != "ZZZ") {
            val toLeft = instructions[instructionIndex] == 'L'
            val (left, right) = network[currentNode]!!

            instructionsExecuted++
            currentNode = if (toLeft) left else right

            instructionIndex++
            if (instructionIndex == instructionLimit) {
                instructionIndex = 0
            }
        }

        return instructionsExecuted
    }

    private fun toNode(it: String): Pair<String, Pair<String, String>> {
        val nodeContent = it.split(" = ")
        val nextNodes = nodeContent[1]
            .substringAfter("(")
            .substringBefore(")")
            .split(", ")
        return nodeContent[0] to Pair(nextNodes[0], nextNodes[1])
    }
}