import Day10.Point2D.Companion.EAST
import Day10.Point2D.Companion.NORTH
import Day10.Point2D.Companion.SOUTH
import Day10.Point2D.Companion.WEST

class Day10 {

    fun part1(fileName: String): Int {
        val grid = FileProvider().readFileAsLines(fileName)
            .map { it.toCharArray() }.toTypedArray()

        val start = grid
            .indexOfFirst { 'S' in it }
            .let { y -> Point2D(grid[y].indexOf('S'), y) }

        var current = start.neighbours()
            .filter { grid.isSafe(it) }
            .first {
                val difference = it - start
                movements.containsKey(grid[it] to difference)
            }

        var steps = 1
        var nextDirection = current - start
        while (current != start) {
            steps++
            nextDirection = movements[grid[current] to nextDirection]!!
            current += nextDirection
        }

        return steps / 2
    }

    private val movements: Map<Pair<Char, Point2D>, Point2D> =
        mapOf(
            ('|' to SOUTH) to SOUTH,
            ('|' to NORTH) to NORTH,
            ('-' to EAST) to EAST,
            ('-' to WEST) to WEST,
            ('L' to WEST) to NORTH,
            ('L' to SOUTH) to EAST,
            ('J' to SOUTH) to WEST,
            ('J' to EAST) to NORTH,
            ('7' to EAST) to SOUTH,
            ('7' to NORTH) to WEST,
            ('F' to WEST) to SOUTH,
            ('F' to NORTH) to EAST
        )

    fun Array<CharArray>.isSafe(at: Point2D) =
        at.y in this.indices && at.x in this[at.y].indices

    operator fun Array<CharArray>.set(at: Point2D, c: Char) {
        this[at.y][at.x] = c
    }

    operator fun Array<CharArray>.get(at: Point2D): Char =
        this[at.y][at.x]

    data class Point2D(val x: Int, val y: Int) {
        fun neighbours(): Set<Point2D> =
            setOf(
                this + NORTH,
                this + EAST,
                this + SOUTH,
                this + WEST
            )

        operator fun minus(other: Point2D): Point2D =
            Point2D(x - other.x, y - other.y)

        operator fun plus(other: Point2D): Point2D =
            Point2D(x + other.x, y + other.y)

        companion object {
            val NORTH = Point2D(0, -1)
            val EAST = Point2D(1, 0)
            val SOUTH = Point2D(0, 1)
            val WEST = Point2D(-1, 0)
        }
    }
}