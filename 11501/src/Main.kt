fun main() {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val prices = readln().split(" ").map { it.toLong() }

        println(maxProfit(prices))
    }
}

fun maxProfit(prices: List<Long>): Long {
    var maxPrice = 0L
    var profit = 0L

    for (i in prices.size - 1 downTo 0) {
        if (prices[i] > maxPrice) {
            maxPrice = prices[i]
        } else {
            profit += maxPrice - prices[i]
        }
    }

    return profit
}