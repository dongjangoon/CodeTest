fun main() {
    val (a, b) = readln().split(" ").map { it.toLong() }
    val gcd = gcd(a, b)
    println("1".repeat(gcd.toInt()))
}

fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}