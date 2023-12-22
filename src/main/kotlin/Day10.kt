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

        return goThroughPipe(start, grid).size / 2
    }

    fun part2(fileName: String): Int {
        val grid = FileProvider().readFileAsLines(fileName)
            .map { it.toCharArray() }.toTypedArray()

        val start = grid
            .indexOfFirst { 'S' in it }
            .let { y -> Point2D(grid[y].indexOf('S'), y) }

        val pipe = goThroughPipe(start, grid)

        replaceAllNonPipeWithDot(grid, pipe)

        val emptyCorner =
            listOf(
                Point2D(0, 0),
                Point2D(0, grid[0].lastIndex),
                Point2D(grid.lastIndex, 0),
                Point2D(grid.lastIndex, grid[0].lastIndex)
            ).first { grid[it] == '.' }

        goThroughPipe(start, grid) { current, direction, nextDirection ->
            grid.fill(current + markingDirection.getValue(direction), 'O')
            if (grid[current] in setOf('7', 'L', 'J', 'F')) {
                grid.fill(current + markingDirection.getValue(nextDirection), 'O')
            }
        }

        return grid.sumOf { row -> row.count { it == '.' } }
    }

    private fun replaceAllNonPipeWithDot(grid: Array<CharArray>, pipe: Set<Point2D>) {
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->
                val at = Point2D(x, y)
                if (at !in pipe) grid[at] = '.'
            }
        }
    }


    private fun goThroughPipe(
        start: Point2D,
        grid: Array<CharArray>,
        preMove: (Point2D, Point2D, Point2D) -> (Unit) = { _, _, _ -> }
    ): Set<Point2D> {
        val pipe = mutableSetOf(start)

        var current = start.neighbours()
            .filter { grid.isSafe(it) }
            .first {
                val difference = it - start
                movements.containsKey(grid[it] to difference)
            }

        var direction = current - start
        while (current != start) {
            pipe += current
            val nextDirection = movements[grid[current] to direction]!!
            preMove(current, direction, nextDirection)
            direction = nextDirection
            current += direction
        }
        return pipe
    }

    private fun Array<CharArray>.fill(at: Point2D, c: Char) {
        if (!isSafe(at)) return
        val queue = ArrayDeque<Point2D>().apply { add(at) }
        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()
            if (this[next] == '.') {
                this[next] = c
                queue.addAll(next.neighbours().filter { isSafe(it) })
            }
        }
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

    private val markingDirection: Map<Point2D, Point2D> =
        mapOf(
            SOUTH to EAST,
            NORTH to WEST,
            WEST to SOUTH,
            EAST to NORTH
        )

    private fun Array<CharArray>.isSafe(at: Point2D) =
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