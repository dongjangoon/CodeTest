import java.util.PriorityQueue

data class Point(val x: Int, val y: Int)
data class Node(val point: Point, val direction: Int, val mirrors: Int): Comparable<Node> {
    override fun compareTo(other: Node): Int = this.mirrors - other.mirrors
}

val dx = intArrayOf(-1, 0, 1, 0) // dir: 상, 우, 하, 좌
val dy = intArrayOf(0, 1, 0, -1)

fun main() {
    val (w, h) = readln().split(" ").map { it.toInt() }
    val map = Array(h) { readln().toCharArray() }

    val lazers = mutableListOf<Point>()
    for (i in 0 until h) {
        for (j in 0 until w) {
            if (map[i][j] == 'C') {
                lazers.add(Point(i, j))
            }
        }
    }

    val result = dijkstra(map, lazers[0], lazers[1], h, w)
    println(result)
}

fun dijkstra(map: Array<CharArray>, start: Point, end: Point, h: Int, w: Int): Int {
    val queue = PriorityQueue<Node>()
    val visited = Array(h) { Array(w) { IntArray(4) { Int.MAX_VALUE } } }

    for (dir in 0..3) {
        queue.offer(Node(start, dir, 0))
        visited[start.x][start.y][dir] = 0
    }

    while (queue.isNotEmpty()) {
        val (current, dir, mirrors) = queue.poll()

        if (current == end) return mirrors

        for (i in 0..3) {
            val nx = current.x + dx[i]
            val ny = current.y + dy[i]

            if (nx in 0 until h && ny in 0 until w && map[nx][ny] != '*') {

                val newMirrors = mirrors + if (i == dir) 0 else 1

                if (newMirrors < visited[nx][ny][i]) {
                    visited[nx][ny][i] = newMirrors
                    queue.offer(Node(Point(nx, ny), i, newMirrors))
                }
            }
        }
    }

    return -1
}