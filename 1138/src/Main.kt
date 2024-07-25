fun main() {
    val n = readln().toInt()
    val tallerThanIndex = readln().split(" ").map { it.toInt() }

    val result = MutableList(n) { 0 }

    for (i in 0 until n) {
        var count = 0
        for (j in 0 until n) {
            if (count == tallerThanIndex[i] && result[j] == 0) {
                result[j] = i + 1
                break
            }
            if (result[j] == 0) count++
        }
    }

    println(result.joinToString(" "))
}