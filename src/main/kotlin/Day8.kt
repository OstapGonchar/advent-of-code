import java.util.function.Predicate

class Day8 {

    fun part1(fileName: String): Int {
        val lines = FileProvider().readFileAsLines(fileName)

        val instructions = lines[0]

        val network = lines
            .drop(2)
            .associate { toNode(it) }

        return countInstructions("AAA", instructions, network) { it != "ZZZ" }
    }

    fun part2(fileName: String): Long {
        val lines = FileProvider().readFileAsLines(fileName)

        val instructions = lines[0]

        val network = lines
            .drop(2)
            .associate { toNode(it) }

        return network.keys
            .filter { it.endsWith("A") }
            .map { node -> countInstructions(node, instructions, network) { !it.endsWith("Z") }.toLong() }
            .reduce { prev, next -> prev.lcm(next) }
    }

    private fun countInstructions(
        startNode: String,
        instructions: String,
        network: Map<String, Pair<String, String>>,
        runCondition: Predicate<String>
    ): Int {
        var currentNode = startNode
        var instructionsExecuted = 0
        var instructionIndex = 0
        val instructionLimit = instructions.length

        while (runCondition.test(currentNode)) {
            val toLeft = instructions[instructionIndex] == 'L'
            val (left, right) = network[currentNode]!!
            currentNode = if (toLeft) left else right

            instructionsExecuted++
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

    fun Long.lcm(other: Long): Long =
        (this * other) / this.gcd(other)

    tailrec fun Long.gcd(other: Long): Long =
        if(other == 0L) this
        else other.gcd(this % other)
}