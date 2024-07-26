fun main() {
    val orders = readln().split(" ").map { it.toInt() }.dropLast(1)
    val dp = Array(orders.size + 1) { Array(5) { IntArray(5) { Int.MAX_VALUE } } }

    dp[0][0][0] = 0

    for (i in 1..orders.size) {
        val current = orders[i-1]
        for (left in 0..4) {
            for (right in 0..4) {
                if (dp[i-1][left][right] != Int.MAX_VALUE) {
                    // Move left foot
                    if (current != right) {
                        dp[i][current][right] = minOf(dp[i][current][right],
                            dp[i-1][left][right] + getCost(left, current))
                    }
                    // Move right foot
                    if (current != left) {
                        dp[i][left][current] = minOf(dp[i][left][current],
                            dp[i-1][left][right] + getCost(right, current))
                    }
                }
            }
        }
    }

    println(dp[orders.size].minOf { it.minOrNull() ?: Int.MAX_VALUE })
}

fun getCost(from: Int, to: Int): Int {
    if (from == to) return 1
    if (from == 0) return 2
    if ((from == 1 && to == 3) || (from == 2 && to == 4) ||
        (from == 3 && to == 1) || (from == 4 && to == 2)) return 4
    return 3

}