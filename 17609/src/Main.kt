fun main() {
    var t = readln().toInt()

    while (t-- > 0) {
        val str = readln()
        println(checkPseudoPalindrome(str))
    }
}

fun checkPseudoPalindrome(str: String): Int {
    var left = 0
    var right = str.length - 1

    while (left < right) {
        if (str[left] != str[right]) {
            if (isPalindrome(str, left + 1, right)) return 1
            if (isPalindrome(str, left, right - 1)) return 1
            return 2 // 일반 문자열
        }
        left++
        right--
    }
    return 0 // 회문
}

fun isPalindrome(str: String, start: Int, end: Int): Boolean {
    var left = start
    var right = end
    while (left < right) {
        if (str[left] != str[right]) return false
        left++
        right--
    }
    return true
}