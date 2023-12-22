import kotlin.math.max
import kotlin.math.min

class Day22 {

    fun part1(fileName: String): Int {
        val bricks = FileProvider().readFileAsLines(fileName)
            .map { Brick.of(it) }
            .sortedBy { brick -> brick.start.z }

        val laidBricks = layBricks(bricks)

        val (supports, supportedBy) = calculateSupports(laidBricks)

        var disintegratedCount = 0
        laidBricks.forEach { brick ->
            val supportedBricks = supports[brick]
            if (supportedBricks.isNullOrEmpty()) {
                disintegratedCount++
            } else {
                if (supportedBricks!!.none { supportedBy[it]!!.size == 1 }) {
                    disintegratedCount++
                }
            }
        }
        return disintegratedCount
    }

    private fun layBricks(bricks: List<Brick>): List<Brick> {
        var moved: Boolean
        val laidBricks = bricks.toMutableList()
        do {
            moved = false
            laidBricks.indices.forEach {
                val brick = laidBricks[it]
                if (laidBricks.none { overlap(brick, it) } && brick.start.z != 1) {
                    laidBricks[it] = brick.down()
                    moved = true
                }
            }

        } while (moved)
        return laidBricks
    }

    private fun overlap(brick1: Brick, brick2: Brick): Boolean =
        max(brick1.start.x, brick2.start.x) <= min(brick1.end.x, brick2.end.x)
                && max(brick1.start.y, brick2.start.y) <= min(brick1.end.y, brick2.end.y)
                && brick1.start.z == brick2.end.z + 1


    private fun calculateSupports(bricks: List<Brick>): Pair<Map<Brick, Set<Brick>>, Map<Brick, Set<Brick>>> {
        val supports = hashMapOf<Brick, HashSet<Brick>>()
        val supportedBy = hashMapOf<Brick, HashSet<Brick>>()

        bricks.forEach { brick ->
            bricks.filter { brick != it && overlap(it, brick) }
                .forEach {
                    supports.computeIfAbsent(brick) { hashSetOf() }.add(it)
                    supportedBy.computeIfAbsent(it) { hashSetOf() }.add(brick)
                }
        }
        return supports to supportedBy
    }

    data class Point3D(val x: Int, val y: Int, val z: Int) {

        fun down(): Point3D {
            return Point3D(x, y, z - 1)
        }

        companion object {
            fun of(s: String): Point3D {
                return s.split(",")
                    .map { it.toInt() }
                    .let { Point3D(it[0], it[1], it[2]) }
            }
        }
    }

    data class Brick(val start: Point3D, val end: Point3D) {

        fun down(): Brick {
            return Brick(start.down(), end.down())
        }

        companion object {
            fun of(s: String): Brick {
                return s.split("~")
                    .map { Point3D.of(it) }
                    .let { Brick(it[0], it[1]) }
            }
        }
    }
}