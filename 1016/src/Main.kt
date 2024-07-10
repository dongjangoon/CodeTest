import kotlin.math.sqrt

fun main() {
    val (min, max) = readln().split(" ").map { it.toLong() }
    println(countSquareFreeNumbers(min, max))
}

fun countSquareFreeNumbers(min: Long, max: Long): Long {
    val sqrtMax = sqrt(max.toDouble()).toLong()
    val primes = generatePrimes(sqrtMax)

    var result = 0L

    fun dfs(depth: Int, num: Long, sign: Int) {
        val count = max / num - (min - 1) / num
        result += sign * count

        for (i in depth until primes.size) {
            if (num * primes[i] * primes[i] > max) break
            dfs(i + 1, num * primes[i] * primes[i], -sign)
        }
    }

    dfs(0, 1L, 1)
    return result
}

fun generatePrimes(n: Long): List<Long> {
    val isPrime = BooleanArray(n.toInt() + 1) { true }
    val primes = mutableListOf<Long>()

    for (i in 2..n.toInt()) {
        if (isPrime[i]) {
            primes.add(i.toLong())
            for (j in (i.toLong() * i..n step i.toLong()).toList()) {
                isPrime[j.toInt()] = false
            }
        }
    }

    return primes
}