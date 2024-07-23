fun main() {
    val t = readln().toInt()

    repeat(t) {
        val w = readln()
        val k = readln().toInt()
        val result = solve(w, k)

        if (result.first == -1) {
            println(-1)
        } else {
            println("${result.first} ${result.second}")
        }
    }
}

fun solve(w: String, k: Int): Pair<Int, Int> {
    if (k > w.length) return Pair(-1, -1)

    val charPositions = List(26) { mutableListOf<Int>() }
    for ((index, char) in w.withIndex()) {
        charPositions[char - 'a'].add(index)
    }

    var minLength = Int.MAX_VALUE
    var maxLength = -1

    for (positions in charPositions) {
        if (positions.size >= k) {
            for (i in 0..positions.size - k) {
                val length = positions[i + k - 1] - positions[i] + 1
                minLength = minOf(minLength, length)
                maxLength = maxOf(maxLength, length)
            }
        }
    }

    return if (minLength == Int.MAX_VALUE) Pair(-1, -1) else Pair(minLength, maxLength)
}