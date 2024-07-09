import kotlin.math.sqrt

fun main() {
    val n = readln().toInt()
    generateAmazingPrimes(n, 0)
}

fun generateAmazingPrimes(n: Int, current: Int) {
    if (n == 0) {
        println(current)
        return
    }

    for (i in 1..9) {
        val next = current * 10 + i
        if (isPrime(next)) {
            generateAmazingPrimes(n - 1, next)
        }
    }
}

fun isPrime(num: Int): Boolean {
    if (num < 2) return false
    if (num == 2) return true
    if (num % 2 == 0) return false

    val sqrtNum = sqrt(num.toDouble()).toInt()
    for (i in 3..sqrtNum step 2) {
        if (num % i == 0) return false
    }
    return true
}
