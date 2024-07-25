data class Shortcut(val start: Int, val end: Int, val distance: Int)

fun main() {
    val (n, d) = readln().split(" ").map { it.toInt() }
    val shortcuts = mutableListOf<Shortcut>()
    repeat(n) {
        val (start, end, distance) = readln().split(" ").map { it.toInt() }
        shortcuts.add(Shortcut(start, end, distance))
    }

    val dp = IntArray(10001) { Int.MAX_VALUE }
    dp[0] = 0

    for (i in 1 .. d) {
        for ((start, end, distance) in shortcuts) {
            if (end == i) {
                dp[i] = minOf(dp[start] + distance, dp[i])
            }
        }
        dp[i] = minOf(dp[i], dp[i - 1] + 1)
    }

    println(dp[d])
}