data class Point(val x: Int, val y: Int)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    val virus = mutableListOf<Point>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == 2) {
                virus.add(Point(i, j))
            }
        }
    }

    val combinations = getCombinationsOfPosition(virus, m)
    var minTime = Int.MAX_VALUE

    for (comb in combinations) {
        val time = bfs(map, comb)
        if (time != -1) {
            minTime = minOf(minTime, time)
        }
    }

    println(if (minTime == Int.MAX_VALUE) -1 else minTime)
}

fun bfs(map: Array<IntArray>, start: List<Point>): Int {
    val n = map.size
    val queue = ArrayDeque<Triple<Int, Int, Int>>()
    val visited = Array(n) { BooleanArray(n) }
    var maxTime = 0

    val dx = intArrayOf(1, -1, 0, 0)
    val dy = intArrayOf(0, 0, 1, -1)

    for (elem in start) {
        queue.add(Triple(elem.x, elem.y, 0))
        visited[elem.x][elem.y] = true
    }

    while (queue.isNotEmpty()) {
        val (x, y, time) = queue.removeFirst()
        maxTime = maxOf(maxTime, time)

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in 0 until n && ny in 0 until n && !visited[nx][ny] && map[nx][ny] != 1) {
                visited[nx][ny] = true
                queue.add(Triple(nx, ny, time + 1))
            }
        }
    }

    return if (isStuck(map, visited)) -1 else maxTime
}

fun isStuck(map : Array<IntArray>, visited : Array<BooleanArray>) : Boolean {
    val n = map.size
    var isStuck = false
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (!visited[i][j] && map[i][j] != 1) {
                isStuck = true
            }
        }
    }

    return isStuck
}

fun getCombinationsOfPosition(virus: MutableList<Point>, m: Int): List<List<Point>> {
    val result = mutableListOf<List<Point>>()

    fun dfs(index: Int, current: List<Point>) {
        if (current.size == m) {
            result.add(current)
            return
        }

        for (i in index until virus.size) {
            dfs(i + 1, current + virus[i])
        }
    }

    dfs(0, emptyList())

    return result
}