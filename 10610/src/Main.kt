fun main() {
    val n = readln()
    if (!checkIsMultipleOf30(n)) {
        println(-1)
        return
    }

    val sortedChars = n.filter { it != '0' }.toCharArray().sortedArrayDescending()
    val zeroCount = n.count { it == '0' }

    val result = String(sortedChars) + "0".repeat(zeroCount)
    println(result)
}

fun checkIsMultipleOf30(n: String): Boolean {
    val sumOfDigits = n.sumOf { it.digitToInt() }
    return sumOfDigits % 3 == 0 && n.contains('0')
}