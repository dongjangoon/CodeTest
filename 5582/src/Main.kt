import kotlin.math.max

fun main() {
    val s = readln()
    val t = readln()
    println(longestCommonSubstring(s, t))
}

fun longestCommonSubstring(s: String, t: String): Int {
    val n = s.length
    val m = t.length
    val dp = Array(n + 1) { IntArray(m + 1) }
    var maxLength = 0

    for (i in 1..n) {
        for (j in 1..m) {
            if (s[i-1] == t[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1
                maxLength = max(maxLength, dp[i][j])
            }
        }
    }

    return maxLength
}