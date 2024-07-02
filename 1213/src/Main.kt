fun main() {
    val str = readln()
    val alphabetCount = IntArray(26)

    for (element in str) {
        alphabetCount[element - 'A']++
    }

    var oddCount = 0
    var oddChar = ' '
    val halfPalindrome = StringBuilder()

    for (i in 0 .. 25) {
        if (alphabetCount[i] % 2 != 0) {
            oddCount++
            oddChar = (i + 'A'.code).toChar()
        }
        repeat(alphabetCount[i] / 2) {
            halfPalindrome.append((i + 'A'.code).toChar())
        }
    }

    if (oddCount > 1) {
        println("I'm Sorry Hansoo")
    } else {
        val result = halfPalindrome.toString() +
                (if (oddCount == 1) oddChar else "") +
                halfPalindrome.reverse()
        println(result)
    }
}