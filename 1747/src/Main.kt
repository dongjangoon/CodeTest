import kotlin.math.sqrt

//import kotlin.math.sqrt
//
//fun main() {
//    var n = readln().toInt()
//
//    while (true) {
//        if (isPrime(n) && isPalindrome(n)) {
//            println(n)
//            return
//        }
//
//        n++
//    }
//}
//
//fun isPrime(n: Int): Boolean {
//    if (n < 2) return false
//    if (n == 2) return true
//    if (n % 2 == 0) return false
//
//    val sqrtNum = sqrt(n.toDouble()).toInt()
//    for (i in 3..sqrtNum step 2) {
//        if (n % i == 0) return false
//    }
//
//    return true
//}
//
//fun isPalindrome(n: Int): Boolean {
//    val str = n.toString().toCharArray()
//
//    for (i in 0 until str.size / 2) {
//        if (str[i] != str[str.size - i - 1]) return false
//    }
//
//    return true
//}

fun main() {
    generateSequence(readln().toInt()) { it + 1 }
        .first { isPrime(it) && isPalindrome(it) }
        .also { println(it) }
}

fun isPrime(n: Int): Boolean = when {
    n < 2 -> false
    n == 2 -> true
    n % 2 == 0 -> false
    else -> (3..sqrt(n.toDouble()).toInt() step 2).none { n % it == 0 }
}

fun isPalindrome(n: Int): Boolean = n.toString().let { it == it.reversed() }