data class Flower(val start: Int, val end: Int)

fun main() {
    val n = readln().toInt()
    val flowers = List(n) {
        val (sm, sd, em, ed) = readln().split(" ").map { it.toInt() }
        Flower(sm * 100 + sd, em * 100 + ed)
    }.sortedBy { it.start }

    var end = 301
    var i = 0
    var count = 0

    while (end <= 1130) {
        var maxEnd = end
        while (i < n && flowers[i].start <= end) {
            maxEnd = maxOf(maxEnd, flowers[i].end)
            i++
        }

        if (maxEnd == end) {
            println(0)
            return
        }

        end = maxEnd
        count++
    }

    println(count)
}