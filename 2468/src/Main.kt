import java.util.*

fun main() {
    val n = readln().toInt()
    val map = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    val maxHeight = map.maxOf { it.max() }

    var maxAreaCount = 1
    for (i in 0 .. maxHeight) {
        maxAreaCount = maxOf(maxAreaCount, getAreaByHeight(n, i, map))
    }

    println(maxAreaCount)
}

fun getAreaByHeight(n: Int, maxHeight: Int, map: Array<IntArray>): Int {
    var result = 0
    val visited = Array(n) { BooleanArray(n) }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (!visited[i][j] && map[i][j] > maxHeight) {
                bfs(i, j, maxHeight, map, visited)
                result++
            }
        }
    }

    return result
}

fun bfs(i: Int, j: Int, maxHeight: Int, map: Array<IntArray>, visited: Array<BooleanArray>) {
    val n = map.size
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(i, j))
    visited[i][j] = true

    val dx = intArrayOf(1, -1, 0, 0)
    val dy = intArrayOf(0, 0, 1, -1)

    while (queue.isNotEmpty()) {
        val (x, y) = queue.removeFirst()

        for (k in 0..3) {
            val nx = x + dx[k]
            val ny = y + dy[k]

            if (nx in 0 until n && ny in 0 until n && !visited[nx][ny] && map[nx][ny] > maxHeight) {
                visited[nx][ny] = true
                queue.add(Pair(nx, ny))
            }
        }
    }
}