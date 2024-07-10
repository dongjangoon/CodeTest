import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val t = readln().toInt()
    val primes = generatePrimes()

    repeat(t) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        println(makePrime(a, b, primes))
    }
}

fun makePrime(a: Int, b: Int, primes: List<Int>): String {
    val visited = BooleanArray(10000) { false }
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(a, 0))

    while (queue.isNotEmpty()) {
        val (cur, count) = queue.removeFirst()

        if (cur == b) {
            return count.toString()
        }

        for (i in 1..4) {
            for (j in 0..9) {
                val newNum = changeDigit(cur, i, j)

                if (!visited[newNum] && newNum in primes) {
                    queue.add(Pair(newNum, count + 1))
                    visited[newNum] = true
                }
            }
        }
    }

    return "Impossible"
}

fun changeDigit(number: Int, position: Int, newDigit: Int): Int {
    val numString = number.toString().toCharArray()
    numString[numString.size - position] = newDigit.toString()[0]
    return String(numString).toInt()
}

fun generatePrimes(): List<Int> {
    val start = 10.0.pow(3).toInt()
    val end = 10.0.pow(4).toInt()

    val isPrime = BooleanArray(end + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    for (i in 2..sqrt(end.toDouble()).toInt()) {
        if (isPrime[i]) {
            for (j in i * i..end step i) {
                isPrime[j] = false
            }
        }
    }

    return (start until end).filter { isPrime[it] }
}