fun main() {
    val n = readln().toInt()
    val scoresByLevel = mutableListOf<Int>()
    repeat(n) {
        scoresByLevel.add(readln().toInt())
    }

    var count = 0
    for (i in n-1 downTo 1) {
        while (scoresByLevel[i] <= scoresByLevel[i-1]) {
            scoresByLevel[i-1]--
            count++
        }
    }

    println(count)
}