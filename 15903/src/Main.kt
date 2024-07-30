import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val scores = readln().split(" ").map { it.toLong() }
    val pq = PriorityQueue<Long>()
    for (score in scores) {
        pq.offer(score)
    }

    repeat(m) {
        val a = pq.poll()
        val b = pq.poll()

        val sum = a + b
        pq.offer(sum)
        pq.offer(sum)
    }

    var answer = 0L
    repeat(n) {
        answer += pq.poll()
    }

    println(answer)
}