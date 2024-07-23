import java.util.PriorityQueue

data class Edge(val to: Int, val cows: Int)
data class Node(val id: Int, val cows: Int): Comparable<Node> {
    override fun compareTo(other: Node): Int = this.cows - other.cows
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Edge>() }

    repeat(m) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        graph[a].add(Edge(b, c))
        graph[b].add(Edge(a, c))
    }

    val result = dijkstra(graph, n)
    println(result)
}

fun dijkstra(graph: Array<MutableList<Edge>>, n: Int): Int {
    val pq = PriorityQueue<Node>()
    val dist = IntArray(n + 1) { Int.MAX_VALUE }

    dist[1] = 0
    pq.offer(Node(1, 0))

    while (pq.isNotEmpty()) {
        val (curr, cows) = pq.poll()

        if (curr == n) return cows
        if (cows > dist[curr]) continue

        for (edge in graph[curr]) {
            val newCows = cows + edge.cows
            if (newCows < dist[edge.to]) {
                dist[edge.to] = newCows
                pq.offer(Node(edge.to, newCows))
            }
        }
    }

    return -1
}

