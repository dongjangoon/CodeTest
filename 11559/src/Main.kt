data class Point(val x: Int, val y: Int)

fun main() {
    val map = Array(12) { readln().toCharArray() }
    var answer = 0
    while (true) {
        val bubbleGroups = findFourBubbles(map)
        if (bubbleGroups.isEmpty()) {
            println(answer)
            return
        }
        simulate(map, bubbleGroups)
        answer++
    }

}

fun findFourBubbles(map: Array<CharArray>): List<List<Point>> {
    val result = mutableListOf<List<Point>>()
    val visited = Array(12) { BooleanArray(6) }
    val dx = intArrayOf(0, 0, -1, 1)
    val dy = intArrayOf(1, -1, 0, 0)

    fun dfs(x: Int, y: Int, char: Char): List<Point> {
        val group = mutableListOf<Point>()
        val stack = ArrayDeque<Point>()
        stack.addLast(Point(x, y))

        while (stack.isNotEmpty()) {
            val (cx, cy) = stack.removeLast()
            if (cx !in 0..11 || cy !in 0..5 || visited[cx][cy] || map[cx][cy] != char) continue
            visited[cx][cy] = true
            group.add(Point(cx, cy))

            for (k in 0..3) {
                val nx = cx + dx[k]
                val ny = cy + dy[k]
                stack.addLast(Point(nx, ny))
            }
        }

        return group
    }

    for (i in 0 until 12) {
        for (j in 0 until 6) {
            if (!visited[i][j] && map[i][j] != '.') {
                val group = dfs(i, j, map[i][j])
                if (group.size >= 4) {
                    result.add(group)
                }
            }
        }
    }

    return result
}

fun simulate(map: Array<CharArray>, bubbleGroups: List<List<Point>>) {
    for (group in bubbleGroups) {
        for (point in group) {
            map[point.x][point.y] = '.'
        }
    }

    for (col in 0..5) {
        var bottom = 11
        for (row in 11 downTo 0) {
            if (map[row][col] != '.') {
                map[bottom][col] = map[row][col]
                if (bottom != row) {
                    map[row][col] = '.'
                }
                bottom--
            }
        }
    }
}