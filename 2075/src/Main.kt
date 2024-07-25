import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    val board = Array(n) { readln().split(" ").map { it.toLong() }.toLongArray() }

    val pq = PriorityQueue<Long>(reverseOrder())
    board.asSequence().flatMap { it.asSequence() }.forEach(pq::offer)
//    for (i in n-1 downTo 0) {
//        for (j in 0 until n) {
//            pq.offer(board[i][j])
//        }
//    }

    repeat(n-1) { pq.poll() }

    println(pq.poll())
}