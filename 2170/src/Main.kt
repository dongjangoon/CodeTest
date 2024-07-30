fun main() {
    val n = readln().toInt()
    val lines = mutableListOf<Pair<Long, Long>>()
    repeat(n) {
        val (x, y) = readln().split(" ").map { it.toLong() }
        lines.add(Pair(x, y))
    }

    lines.sortBy { it.first }

    var totalLength = 0L
    var currentStart = lines[0].first
    var currentEnd = lines[0].second

    for (i in 1 until lines.size) {
        val (start, end) = lines[i]
        if (start <= currentEnd) {
            currentEnd = maxOf(currentEnd, end)
        } else {
            totalLength += currentEnd - currentStart
            currentStart = start
            currentEnd = end
        }
    }

    totalLength += currentEnd - currentStart

    println(totalLength)
}