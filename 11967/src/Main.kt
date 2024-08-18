import java.util.LinkedList
import java.util.Queue

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val switchesByRoom = Array(n+1) { Array(n+1) { mutableListOf<Pair<Int, Int>>() } }
    repeat(m) {
        val (x, y, a, b) = readln().split(" ").map { it.toInt() }
        switchesByRoom[x][y].add(Pair(a, b))
    }

    val rooms = Array(n+1) { BooleanArray(n+1) }
    rooms[1][1] = true
    val reachable = Array(n+1) { BooleanArray(n+1) }
    reachable[1][1] = true

    val dx = intArrayOf(1, -1, 0, 0)
    val dy = intArrayOf(0, 0, 1, -1)

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(Pair(1, 1))

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()

        for (room in switchesByRoom[x][y]) {
            val (a, b) = room
            if (!rooms[a][b]) {
                rooms[a][b] = true

                for (k in 0..3) {
                    val nx = a + dx[k]
                    val ny = b + dy[k]
                    if (nx in 1 until n+1 && ny in 1 until n+1 && reachable[nx][ny]) {
                        reachable[a][b] = true
                        queue.add(Pair(a, b))
                        break
                    }
                }
            }
        }

        for (k in 0..3) {
            val nx = x + dx[k]
            val ny = y + dy[k]
            if (nx in 1 until n+1 && ny in 1 until n+1 && rooms[nx][ny] && !reachable[nx][ny]) {
                reachable[nx][ny] = true
                queue.add(Pair(nx, ny))
            }
        }
    }

    println(rooms.sumOf { it.count { it } })
}