import java.util.LinkedList
import java.util.Queue
import kotlin.math.abs

fun main() {
    val grid = Array(5) { readln().toCharArray() }
    var result = 0

    fun isAdjacent(pos1: Int, pos2: Int): Boolean {
        val r1 = pos1 / 5
        val c1 = pos1 % 5
        val r2 = pos2 / 5
        val c2 = pos2 % 5
        return abs(r1 - r2) + abs(c1 - c2) == 1
    }

    fun checkConnectivity(selected: IntArray): Boolean {
        val visited = BooleanArray(7)
        val queue: Queue<Int> = LinkedList()
        visited[0] = true
        queue.offer(0)
        var connectedCount = 1

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            for (i in 1 until 7) {
                if (!visited[i] && isAdjacent(selected[current], selected[i])) {
                    visited[i] = true
                    queue.offer(i)
                    connectedCount++
                }
            }
        }

        return connectedCount == 7
    }

    fun dfs(selected: IntArray, count: Int, sCount: Int, lastPos: Int) {
        if (count == 7) {
            if (sCount >= 4 && checkConnectivity(selected)) {
                result++
            }
            return
        }

        for (i in lastPos + 1 until 25) {
            val row = i / 5
            val col = i % 5
            selected[count] = i
            if (grid[row][col] == 'S') {
                dfs(selected, count + 1, sCount + 1, i)
            } else {
                dfs(selected, count + 1, sCount, i)
            }
        }
    }

    val selected = IntArray(7)
    dfs(selected, 0, 0, -1)

    println(result)
}