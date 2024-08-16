import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    val visited = Array(n) { BooleanArray(m) }
    var count = 0
    var maxArea = 0

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (!visited[i][j] && map[i][j] == 1) {
                maxArea = maxOf(maxArea, bfs(n, m, i, j, visited, map))
                count++
            }
        }
    }

    println(count)
    println(maxArea)
}

fun bfs(n: Int, m: Int, i: Int, j: Int, visited: Array<BooleanArray>, map: Array<IntArray>): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    var result = 1
    queue.add(Pair(i, j))
    visited[i][j] = true

    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, 1, -1)

    while (queue.isNotEmpty()) {
        val (x, y) = queue.removeFirst()

        for (k in 0..3) {
            val nx = x + dx[k]
            val ny = y + dy[k]

            if (nx in 0 until n && ny in 0 until m && !visited[nx][ny] && map[nx][ny] == 1) {
                queue.add(Pair(nx, ny))
                visited[nx][ny] = true
                result++
            }
        }
    }

    return result
}