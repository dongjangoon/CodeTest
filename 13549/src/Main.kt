import java.util.PriorityQueue

data class State(val x: Int, val time: Int): Comparable<State> {
    override fun compareTo(other: State): Int = this.time - other.time
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val result = dijkstra(n, k)
    println(result)
}

fun dijkstra(n: Int, k: Int): Int {
    val pq = PriorityQueue<State>()
    val visited = IntArray(100_001) { Int.MAX_VALUE }

    pq.offer(State(n, 0))
    visited[n] = 0

    while (pq.isNotEmpty()) {
        val (curr, time) = pq.poll()

        if (curr == k) return time

        if (curr + 1 < 100_000 && time + 1 < visited[curr + 1]) {
            pq.offer(State(curr + 1, time + 1))
            visited[curr + 1] = time + 1
        }

        if (curr - 1 >= 0 && time + 1 < visited[curr - 1]) {
            pq.offer(State(curr - 1, time + 1))
            visited[curr - 1] = time + 1
        }

        if (curr * 2 <= 100_000 && time < visited[curr * 2]) {
            pq.offer(State(curr * 2, time))
            visited[curr * 2] = time
        }
    }

    return -1
}