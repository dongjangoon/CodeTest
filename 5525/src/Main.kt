fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val s = readln()

    var result = 0
    var patternCount = 0
    var i = 1

    while (i < m - 1) {
        if (s[i-1] == 'I' && s[i] == 'O' && s[i+1] == 'I') {
            patternCount++
            if (patternCount == n) {
                result++
                patternCount--
            }
            i += 2
        } else {
            patternCount = 0
            i++
        }
    }

    println(result)
}