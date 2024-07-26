import java.util.LinkedList
import java.util.Queue

data class Point(val x: Int, val y: Int)

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (w, h) = readln().split(" ").map { it.toInt() }
        val map = Array(h) { readln().toCharArray() }

        var sangeun = Point(-1, -1)
        val fires = LinkedList<Point>()
        for (i in 0 until h) {
            for (j in 0 until w) {
                if (map[i][j] == '@') {
                    sangeun = Point(i, j)
                } else if (map[i][j] == '*') {
                    fires.add(Point(i, j))
                }
            }
        }

        val result = simulate(map, fires, sangeun, w, h)

        println(if (result == -1) "IMPOSSIBLE" else result)
    }
}

fun simulate(map: Array<CharArray>, fires: Queue<Point>, sangeun: Point, w: Int, h: Int): Int {
    val queue = LinkedList<Point>()
    val visited = Array(h) { BooleanArray(w) { false } }

    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, -1, 0, 1)

    queue.offer(sangeun)
    visited[sangeun.x][sangeun.y] = true

    var time = 0
    while (queue.isNotEmpty()) {
        // fire move
        val fireSize = fires.size
        repeat(fireSize) {
            val (x, y) = fires.poll()

            for (k in 0..3) {
                val nx = x + dx[k]
                val ny = y + dy[k]
                if (nx in 0 until h && ny in 0 until w && map[nx][ny] != '#' && map[nx][ny] != '*') {
                    fires.add(Point(nx, ny))
                    map[nx][ny] = '*'
                }
            }
        }

        // Sangeun move
        val sangeunSize = queue.size
        repeat(sangeunSize) {
            val curr = queue.poll()

            for (k in 0..3) {
                val nx = curr.x + dx[k]
                val ny = curr.y + dy[k]

                if (nx !in 0 until h || ny !in 0 until w) {
                    return time + 1
                }

                if (!visited[nx][ny] && map[nx][ny] != '#' && map[nx][ny] != '*') {
                    queue.offer(Point(nx, ny))
                    visited[nx][ny] = true
                }
            }
        }

        time++
    }

    return -1
}
